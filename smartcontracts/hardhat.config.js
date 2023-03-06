require("@nomiclabs/hardhat-waffle");
// hardhat.config.js

require('@nomiclabs/hardhat-ethers');
require('@openzeppelin/hardhat-upgrades');
require('dotenv').config();

process.env.NODE_TLS_REJECT_UNAUTHORIZED='0'

/**
 * @type import('hardhat/config').HardhatUserConfig
 */
module.exports = {
  solidity: {
    compilers: [
      {
        version: "0.7.3"
      },
      {
        version: "0.8.0"
      },
      {
        version: "0.8.1"
      },
      {
        version: "0.8.2"
      },
      {
        version: "0.8.9"
      }
    ]
  },
  networks: {
    miRed: {
          url: `http://localhost:8545`,
          accounts: [`0x${process.env.ADMIN_PRIV_KEY}`]
        }
  }
};
