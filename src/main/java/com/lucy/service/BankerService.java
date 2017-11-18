package com.lucy.service;

import java.util.List;

import com.lucy.domain.Banker;

public interface BankerService {
	public void save(Banker banker);
	public Banker getBanker(long id);
	public List<Banker> getAllBankers();
	public void updateBanker(long id, Banker banker);
	public void deleteBanker(long id);
	public Banker findBankerByUsername(String userName);
	
	
}
