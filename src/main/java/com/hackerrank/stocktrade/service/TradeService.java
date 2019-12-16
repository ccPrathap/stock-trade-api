package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;

	public void deleteTrades() {
		tradeRepository.deleteAll();
	}

	public Trade createTrade(Trade trade) {
		return tradeRepository.save(trade);
	}

	public Trade findTradeById(Long id) {
		return tradeRepository.findById(id).orElse(null);
	}

	public List<Trade> findAllTrades() {
		return tradeRepository.findAll();
	}

	public List<Trade> findAllTradesByUserId(Long id) {
		List<Trade> trades = tradeRepository.findAll();
		return trades.stream().filter(trade -> id.equals(trade.getUser().getId())).collect(Collectors.toList());
	}

	public List<Trade> findAllTradesByStockSymbol(String symbol) {
		return tradeRepository.findBySymbol(symbol);
	}

	public List<Trade> findAllTradesByUserIdAndStockId(Long id, String symbol) {
		List<Trade> trades = tradeRepository.findBySymbol(symbol);
		return trades.stream().filter(trade -> id.equals(trade.getUser().getId())).collect(Collectors.toList());
	}

}
