package com.exam.exSpring.bbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bbsServiceImpl")
public class BbsServiceImpl implements BbsService{
	
	@Autowired
	private BbsDao bbsDao;

	@Override
	public BbsVO selectBbs(String bbsNo) {
		return bbsDao.selectBbs(bbsNo);
	}

	@Override
	public List<BbsVO> selectBbsList(SearchInfo searchInfo) {
		return bbsDao.selectBbsList(searchInfo);
	}

	@Override
	public int insertBbs(BbsVO bbs) {
		return bbsDao.insertBbs(bbs);
	}

	@Override
	public int updateBbs(BbsVO bbs) {
		return bbsDao.updateBbs(bbs);
	}

	@Override
	public int deleteBbs(String bbsNo) {
		return bbsDao.deleteBbs(bbsNo);
	}

}
