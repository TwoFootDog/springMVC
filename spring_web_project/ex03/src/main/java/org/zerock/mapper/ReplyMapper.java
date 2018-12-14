package org.zerock.mapper;

import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
    public int insert(ReplyVO replyVO);

    public ReplyVO read(Long bno);

    public int delete(Long bno);

    public int update(ReplyVO replyVO);
}
