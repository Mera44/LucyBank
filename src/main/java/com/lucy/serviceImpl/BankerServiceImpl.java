package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Banker;
import com.lucy.repository.BankerRepository;
import com.lucy.service.BankerService;

@Service
public class BankerServiceImpl implements BankerService{
	@Autowired
	BankerRepository bankerRepository;
	@Override
	public void save(Banker banker) {
		bankerRepository.save(banker);
	}

	@Override
	public Banker getBanker(long id) {
		return bankerRepository.findOne(id);
	}

	@Override
	public List<Banker> getAllBankers() {
		return (List<Banker>) bankerRepository.findAll(); 
	}

	@Override
	public void deleteBanker(long id) {
		Banker banker = bankerRepository.findOne(id);
		if(banker==null) {
			//handle Error here
		}
		bankerRepository.delete(id);
	}

	@Override
	public Banker findBankerByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBanker(long id, Banker banker) {
		Banker updatableBanker = bankerRepository.findOne(id);
		if(updatableBanker==null) {
			//handle Error here
		}
		banker.setId(id);
		bankerRepository.save(banker);
	}

}
