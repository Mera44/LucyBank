package com.lucy.service;

import java.util.List;

import com.lucy.domain.Teller;

public interface TellerService {
	public Teller save(Teller teller);
	public Teller getTeller(long id);
	public List<Teller> getAllTellers();
	public void updateTeller(long id, Teller teller);
	public void deleteTeller(long id);
	public Teller findTellerByUsername(String userName);
}
