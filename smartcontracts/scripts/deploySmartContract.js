async function main() {
    const [deployer] = await ethers.getSigners();

    console.log("Deploying contracts with the account:", deployer.address);

    console.log("Account balance:", (await deployer.getBalance()).toString());

    const UpgrableTokenTest = await ethers.getContractFactory("UpgradableTokenTest");
    const token = await upgrades.deployProxy(UpgrableTokenTest, ["TestToken", "SPM", 2, 100000]);

    console.log("Token address:", token.address);
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });