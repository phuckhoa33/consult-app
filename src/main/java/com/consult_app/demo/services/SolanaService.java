package com.consult_app.demo.services;

public interface SolanaService {
    void createWallet(String mnemonic, String passphrase);

    String signAndSendTransaction(String receiverAddress);

    void listenAndAccountUpdates();

    Long getBalance();
}
