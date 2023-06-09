package com.spring.service;

import java.util.List;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;

public interface BoardService {
	//전체 리스트 가져오기
	public List<BoardDTO> getList(Criteria cri);
	//글등록
	public boolean insert(BoardDTO dto);
	//특정 게시글 조회
	public BoardDTO getRow(int bno);
	//글수정
	public boolean update(BoardDTO dto);
	//글삭제
	public boolean delete(int bno);
	//전체 게시물 개수
	public int getTotalCnt(Criteria cri);
	
	//첨부 파일 가져오기
	public List<AttachFileDTO> getAttachList(int bno);
}
