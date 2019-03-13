package com.commons.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RereadableRequestWrapper extends HttpServletRequestWrapper {

    private boolean parametersParsed = false;

    private final Charset encoding;
    private byte[] rawData;
    private final Map<String, ArrayList<String>> parameters = new LinkedHashMap<String, ArrayList<String>>();
    ByteChunk tmpName = new ByteChunk();
    ByteChunk tmpValue = new ByteChunk();

    private class ByteChunk {

        private byte[] buff;
        private int start = 0;
        private int end;

        public void setByteChunk(byte[] b, int off, int len) {
            buff = b;
            start = off;
            end = start + len;
        }

        public byte[] getBytes() {
            return buff;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public void recycle() {
            buff = null;
            start = 0;
            end = 0;
        }
    }


    public RereadableRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        String characterEncoding = request.getCharacterEncoding();
        if (StringUtils.isBlank(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        this.encoding = Charset.forName(characterEncoding);

        // Convert InputStream data to byte array and store it to this wrapper instance.
        try {
            InputStream inputStream = request.getInputStream();
            this.rawData = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    @Override
    public ServletRequest getRequest() {
        return super.getRequest();
    }

    @Override
    public String getParameter(String name) {
        if (!parametersParsed) {
            parseParameters();
        }
        ArrayList<String> values = this.parameters.get(name);
        if (values == null || values.size() == 0)
            return null;
        return values.get(0);
    }

    public HashMap<String, String[]> getParameters() {
        if (!parametersParsed) {
            parseParameters();
        }
        HashMap<String, String[]> map = new HashMap<String, String[]>(this.parameters.size() * 2);
        for (String name : this.parameters.keySet()) {
            ArrayList<String> values = this.parameters.get(name);
            map.put(name, values.toArray(new String[values.size()]));
        }
        return map;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map getParameterMap() {
        return getParameters();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Enumeration getParameterNames() {
        return new Enumeration<String>() {
            @SuppressWarnings("unchecked")
            private String[] arr = (String[])(getParameterMap().keySet().toArray(new String[0]));
            private int index = 0;

            @Override
            public boolean hasMoreElements() {
                return index < arr.length;
            }

            @Override
            public String nextElement() {
                return arr[index++];
            }
        };
    }

    @Override
    public String[] getParameterValues(String name) {
        if (!parametersParsed) {
            parseParameters();
        }
        ArrayList<String> values = this.parameters.get(name);
        String[] arr = values.toArray(new String[values.size()]);
        if (arr == null) {
            return null;
        }
        return arr;
    }

    private void parseParameters() {
        parametersParsed = true;

        if (!("application/x-www-form-urlencoded".equalsIgnoreCase(super.getContentType()))) {
            // Store parameters to this wrapper instance for URL parameters .
            @SuppressWarnings("unchecked") Enumeration<String> parameterNames = super.getRequest().getParameterNames();
            if (parameterNames.hasMoreElements()) {
                parametersParsed = true;
                while (parameterNames.hasMoreElements()) {
                    String parameterName = parameterNames.nextElement();
                    String[] parameterValues = super.getRequest().getParameterValues(parameterName);
                    this.parameters.put(parameterName, new ArrayList<String>(Arrays.asList(parameterValues)));
                }
            }
            return;
        }

        int pos = 0;
        int end = this.rawData.length;

        while (pos < end) {
            int nameStart = pos;
            int nameEnd = -1;
            int valueStart = -1;
            int valueEnd = -1;

            boolean parsingName = true;
            boolean decodeName = false;
            boolean decodeValue = false;
            boolean parameterComplete = false;

            do {
                switch (this.rawData[pos]) {
                    case '=':
                        if (parsingName) {
                            // Name finished. Value starts from next character
                            nameEnd = pos;
                            parsingName = false;
                            valueStart = ++pos;
                        } else {
                            // Equals character in value
                            pos++;
                        }
                        break;
                    case '&':
                        if (parsingName) {
                            // Name finished. No value.
                            nameEnd = pos;
                        } else {
                            // Value finished
                            valueEnd = pos;
                        }
                        parameterComplete = true;
                        pos++;
                        break;
                    case '%':
                    case '+':
                        // Decoding required
                        if (parsingName) {
                            decodeName = true;
                        } else {
                            decodeValue = true;
                        }
                        pos++;
                        break;
                    default:
                        pos++;
                        break;
                }
            } while (!parameterComplete && pos < end);

            if (pos == end) {
                if (nameEnd == -1) {
                    nameEnd = pos;
                } else if (valueStart > -1 && valueEnd == -1) {
                    valueEnd = pos;
                }
            }

            if (nameEnd <= nameStart) {
                continue;
                // ignore invalid chunk
            }

            tmpName.setByteChunk(this.rawData, nameStart, nameEnd - nameStart);
            if (valueStart >= 0) {
                tmpValue.setByteChunk(this.rawData, valueStart, valueEnd - valueStart);
            } else {
                tmpValue.setByteChunk(this.rawData, 0, 0);
            }

            try {
                String name;
                String value;

                if (decodeName) {
                    name = new String(URLCodec.decodeUrl(Arrays.copyOfRange(tmpName.getBytes(), tmpName.getStart(), tmpName.getEnd())), this.encoding);
                } else {
                    name = new String(tmpName.getBytes(), tmpName.getStart(), tmpName.getEnd() - tmpName.getStart(), this.encoding);
                }

                if (valueStart >= 0) {
                    if (decodeValue) {
                        value = new String(URLCodec.decodeUrl(Arrays.copyOfRange(tmpValue.getBytes(), tmpValue.getStart(), tmpValue.getEnd())), this.encoding);
                    } else {
                        value = new String(tmpValue.getBytes(), tmpValue.getStart(), tmpValue.getEnd() - tmpValue.getStart(), this.encoding);
                    }
                } else {
                    value = "";
                }

                if (StringUtils.isNotBlank(name)) {
                    ArrayList<String> values = this.parameters.get(name);
                    if (values == null) {
                        values = new ArrayList<String>(1);
                        this.parameters.put(name, values);
                    }
                    if (StringUtils.isNotBlank(value)) {
                        values.add(value);
                    }
                }
            } catch (DecoderException e) {
                // ignore invalid chunk
                e.printStackTrace();
            }

            tmpName.recycle();
            tmpValue.recycle();
        }
    }
}
