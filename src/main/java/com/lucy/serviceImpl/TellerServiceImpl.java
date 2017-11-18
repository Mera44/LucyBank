package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Teller;
import com.lucy.repository.TellerRepository;
import com.lucy.service.TellerService;

@Service
public class TellerServiceImpl implements TellerService{
	
	@Autowired
	TellerRepository tellerRepository;
	
	@Override
	public void save(Teller teller) {
		tellerRepository.save(teller);
	}

	@Override
	public Teller getTeller(long id) {
		return tellerRepository.findOne(id);
	}

	@Override
	public List<Teller> getAllTellers() {
		return (List<Teller>) tellerRepository.findAll();
	}

	@Override
	public void updateTeller(long id, Teller teller) {
		Teller updatableTeller = tellerRepository.findOne(id);
		if(updatableTeller==null) {
			//handle Error here
		}
		teller.setId(id);
		tellerRepository.save(teller);
	}

	@Override
	public void deleteTeller(long id) {
		Teller teller = tellerRepository.findOne(id);
		if(teller==null) {
			//handle Error here
		}
		tellerRepository.delete(id);
	}

	@Override
	public Teller findTellerByUsername(String userName) {
		return tellerRepository.findTellerByUsername(userName);
	}

}
