package com.iu.board;

public interface BoardReply {
	
	//reply
	public int reply(BoardReplyDTO boardReplyDTO) throws Exception;
	
	//replyUpdate
	public int replyUpdate(BoardReplyDTO boardReplyDTO) throws Exception;

}
