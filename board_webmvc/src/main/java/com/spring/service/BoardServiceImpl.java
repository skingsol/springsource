package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.mapper.AttachMapper;
import com.spring.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	@Autowired
	private AttachMapper attachMapper;
	
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {		
		return mapper.list(cri);
	}
	
	@Transactional
	@Override
	public boolean insert(BoardDTO dto) {		
		// board 테이블 + attach 테이블 등록
		boolean insertFlag= mapper.insert(dto)==1?true:false;
		
		if(dto.getAttachList() == null || dto.getAttachList().size() == 0) {
			return insertFlag;
		}
		
		dto.getAttachList().forEach(attach ->{
			attach.setBno(dto.getBno());
			attachMapper.insert(attach);
		});
		
		return insertFlag;
	}

	@Override
	public BoardDTO getRow(int bno) {		
		return mapper.get(bno);
	}
	
	@Override
	public boolean update(BoardDTO dto) {		
		return mapper.update(dto)==1?true:false;
	}
	@Override
	public boolean delete(int bno) {		
		return mapper.delete(bno)==1?true:false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {		
		return mapper.totalCnt(cri);
	}
}














