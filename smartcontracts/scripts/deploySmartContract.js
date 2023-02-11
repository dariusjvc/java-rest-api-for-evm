async function main() {
    const [deployer, bank1, bank2, bank3] = await ethers.getSigners();

    console.log("Deploying contracts with the account:", deployer.address);

    console.log("Account balance:", (await deployer.getBalance()).toString());

    const UpgrableTokenTest = await ethers.getContractFactory("UpgradableTokenTest");
    const token = await upgrades.deployProxy(UpgrableTokenTest, ["TestToken", "SPM", 2, 100000]);
    /*await tokenUpgradebale.createBank("Bank1", bank1.address);
    await tokenUpgradebale.createBank("Bank2", bank2.address);
    await tokenUpgradebale.createBank("Bank3", bank3.address);*/

    console.log("Token address:", token.address);
    /*console.log("Bank1 address:", bank1.address);
    console.log("Bank2 address:", bank2.address);
    console.log("Bank3 address:", bank3.address);*/
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });