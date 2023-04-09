const { expect } = require("chai");
const { Instruction } = require("hardhat/internal/hardhat-network/stack-traces/model");

describe("Token contract", function () {


    let instance;
    let owner;
    let addr1;
    let addr2;
    let addr3;
    let addrs;
    let tokenName;
    let tokenSymbol;
    let decimals;
    let newDecimals;
    let initialSupply;
    let Alice;
    let Bob;


    beforeEach(async function () {
        TokenUpgradeable = await ethers.getContractFactory("UpgradableTokenTest");
        [owner, addr1, addr2, addr3, ...addrs] = await ethers.getSigners();
        tokenName = "TestToken";
        tokenSymbol = "$T"
        Alice = addr1;
        Bob = addr2;
        decimals = 8;
        newDecimals = 10;
        initialSupply = 10000;
        instance = await upgrades.deployProxy(TokenUpgradeable, [tokenName, tokenSymbol, decimals, initialSupply]);

    })

    describe("Deployment", function () {

        it("Should set the right owner", async function () {

            expect(await instance.getOwner()).to.equal(owner.address);
        });

        it("Deployment should assign the total supply of tokens to the owner", async function () {

            const ownerBalance = await instance.balanceOf(owner.address);
            expect(await instance.totalSupply()).to.equal(ownerBalance);
        });

        it("Should set the right number of decimals", async function () {
            expect(await instance.decimals()).to.equal(decimals);
        });

        it("Only owner can set number of decimals", async function () {
            await expect(instance.connect(addr1).setDecimals(8)).to.be.reverted;
        });
    });

    describe("Product Funcionalities", function () {
        it("Create tokens", async function () {
            expect(await instance.totalSupply()).to.equal(initialSupply);
            await instance.mintNewTokens(1000);
            expect(await instance.totalSupply()).to.equal(initialSupply + 1000);
        });
    });
});


