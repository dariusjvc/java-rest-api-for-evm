//SPDX-License-Identifier: Undefined

pragma solidity ^0.8.0;
import "@openzeppelin/contracts-upgradeable/token/ERC20/ERC20Upgradeable.sol";
import "@openzeppelin/contracts/utils/math/SafeMath.sol";
import "@openzeppelin/contracts-upgradeable/proxy/utils/Initializable.sol";
import "@openzeppelin/contracts-upgradeable/access/OwnableUpgradeable.sol";

// This is the main building block for smart contracts.
contract UpgradableTokenTest is
Initializable,
ERC20Upgradeable,
OwnableUpgradeable
{
    using SafeMath for uint256;
    using SafeMath for uint8;

    address private _owner;
    uint8 private _decimals;
    uint256 private _totalMinted;

    function initialize(
        string memory tokenName,
        string memory tokenSymbol,
        uint8 tokenDecimals,
        uint256 initialSupply
    ) external initializer {
        __ERC20_init(tokenName, tokenSymbol);
        __Ownable_init();
        _owner = msg.sender;
        _decimals = tokenDecimals;
        _mint(_owner, initialSupply);
        _totalMinted = initialSupply;
    }

    function getOwner() public view returns (address) {
        return _owner;
    }

    function mintNewTokens(uint256 newTokens) public onlyOwner {
        _mint(_owner, newTokens);
        _totalMinted = _totalMinted.add(newTokens);
    }

    function decimals() public view virtual override returns (uint8) {
        return _decimals;
    }

    function setDecimals(uint8 tokenDecimals) external onlyOwner {
        _decimals = tokenDecimals;
    }
    function getTotalMinted() public view returns (uint256) {
        return _totalMinted;
    }
}
