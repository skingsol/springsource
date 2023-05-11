package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BoardDTO;
import com.spring.mapper.BoardMapper;


@Service("board")
public class BoardServiceImpl implements BoardService {
	
	@Autowired // 있던거 주입해주세요
	private BoardMapper Mapper;
	
	@Override  // 빈생성해주세요
	public boolean insertBoard(BoardDTO dto) {
		return Mapper.insert(dto) == 1 ? true : false;
	}

	@Override
	public boolean updateBoard(BoardDTO dto) {
		return Mapper.update(dto)== 1 ? true : false;
	}

	@Override
	public boolean deleteBoard(int bno) {
		return Mapper.delete(bno)== 1 ? true : false;
	}
	
	@Override
	public BoardDTO getRow(int bno) {
		return Mapper.getRow(bno);
	}

	@Override
	public List<BoardDTO> getRows() {
		return Mapper.getRows();
	}

}
