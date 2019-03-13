package blockchain_poc_1;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Main {

	public static int difficulty = 5;

	public static ArrayList<Block> blockChain = new ArrayList<Block>();

	public static void main(String[] args) {

		blockChain.add(new Block("Here we go in first block", "0"));
		System.out.println("Trying to mine block 1...");
		blockChain.get(0).mineBlock(difficulty);

		blockChain.add(new Block("i am in second block buddy", blockChain.get(blockChain.size() - 1).hash));
		System.out.println("Trying to mine block 2...");
		blockChain.get(1).mineBlock(difficulty);

		blockChain.add(new Block("who said, i am in third block", blockChain.get(blockChain.size() - 1).hash));
		System.out.println("Trying to mine block 3...");
		blockChain.get(2).mineBlock(difficulty);

		System.out.println("\nBlockchain is valid : " + isChainValid());

		String blockChainGson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println("\nThe Blockchain : ");
		System.out.println(blockChainGson);

	}

	// integrity check
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;

		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for (int i = 1; i < blockChain.size(); i++) {
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i - 1);

			if (currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hashes not equal");
				// any change to the blockchain'block will cause this method to
				// return false
				return false;
			}

			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				// any change to the blockchain'block will cause this method to
				// return false
				System.out.println("Previous hashes not equal");
				return false;
			}
			
			if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
				System.out.println("This block has not been mined");
				return false;
			}
		}
		return true;
	}
}
