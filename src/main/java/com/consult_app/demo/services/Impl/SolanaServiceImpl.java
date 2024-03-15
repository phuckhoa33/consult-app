package com.consult_app.demo.services.Impl;

import org.springframework.stereotype.Service;

import com.consult_app.demo.services.SolanaService;
import com.paymennt.crypto.bip32.Network;
import com.paymennt.crypto.bip32.wallet.AbstractWallet.Chain;
import com.paymennt.solanaj.api.rpc.Cluster;
import com.paymennt.solanaj.api.rpc.SolanaRpcClient;
import com.paymennt.solanaj.data.SolanaAccount;
import com.paymennt.solanaj.data.SolanaPublicKey;
import com.paymennt.solanaj.data.SolanaTransaction;
import com.paymennt.solanaj.program.SystemProgram;
import com.paymennt.solanaj.wallet.SolanaWallet;

@Service
public class SolanaServiceImpl implements SolanaService {
    private SolanaWallet solanaWallet;

    private SolanaRpcClient client;

    @Override
    public void createWallet(String mnemonic, String passphrase) {
        // the netowrk, MAINNET or TESTNET
        final Network network = Network.TESTNET;

        // create wallet
        solanaWallet = new SolanaWallet(mnemonic, passphrase, network);

        // get address (account, chain, index), used to receive
        solanaWallet.getAddress(0, Chain.EXTERNAL, null);

        // get private key (account, chain, index), used to sign transactions
        solanaWallet.getPrivateKey(0, Chain.EXTERNAL, null);
    }

    @Override
    public String signAndSendTransaction(String receiverAddress) {
        // create new SolanaRpcClient, (DEVNET, TESTNET, MAINNET)
        client = new SolanaRpcClient(Cluster.DEVNET);

        // amount to transfer in lamports, 1 SOL = 1000000000 lamports
        long amount = 1000000000;

        // create new transaction
        SolanaTransaction transaction = new SolanaTransaction();

        // create solana account, this account holds the funds that we want to transfer
        SolanaAccount account = new SolanaAccount(solanaWallet.getPrivateKey(0, Chain.EXTERNAL, null));

        // define the sender and receiver public keys
        SolanaPublicKey fromPublicKey = account.getPublicKey();
        SolanaPublicKey toPublickKey = new SolanaPublicKey(receiverAddress);

        // add instructions to the transaction (from, to, lamports)
        transaction.addInstruction(SystemProgram.transfer(fromPublicKey, toPublickKey, amount));

        // set the recent blockhash
        transaction.setRecentBlockHash(client.getApi().getRecentBlockhash());

        // set the fee payer
        transaction.setFeePayer(account.getPublicKey());

        // sign the transaction
        transaction.sign(account);

        // publish the transaction
        String signature = client.getApi().sendTransaction(transaction);

        return signature;
    }

    @Override
    public void listenAndAccountUpdates() {
        // String walletAddress = "EPBkAFmzU6CajVCjz2Jd3H5MZ7CuZz74UjuWK1MUtFtV";
        // websocket.accountSubscribe(walletAddress, data -> {
        // System.out.println("update received");
        // });
    }

    @Override
    public Long getBalance() {

        return null;
    }

}
