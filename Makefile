dependencies-web3j:
	curl -L --output web3j-1.4.1.tar https://github.com/web3j/web3j-cli/releases/download/v1.4.1/web3j-1.4.1.tar
	rm -rf web3j-4.8.4 && tar -xvf web3j-1.4.1.tar
	rm web3j-1.4.1.tar

wrapper-smart-contract:
	./web3j-1.4.1/bin/web3j generate solidity \
	-a=abis/UpgradableTokenTest.abi \
	-b=abis/UpgradableTokenTest.bin \
	-o ./src/main/java/ \
	-p com.company.bcpayments.wrapper