# How to deploy the UpgradableTokenTest.sol:

```shell
npm install --save-dev hardhat
npx hardhat
npm install @openzeppelin/contracts-upgradeable
npm install @openzeppelin/hardhat-upgrades
npm install @openzeppelin/contracts
npm install --save-dev @nomiclabs/hardhat-waffle 'ethereum-waffle@^3.0.0' @nomiclabs/hardhat-ethers 'ethers@^5.0.0'
npm install dotenv
npx hardhat compile
npx hardhat run scripts/deploySmartContract.js --network miRed
npx hardhat console --network miRed
```

Run the tests with:
```shell
npx hardhat test
```