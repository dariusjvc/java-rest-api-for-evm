package com.company.bcpayments.wrapper;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class UpgradableTokenTest extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b50612197806100206000396000f3fe608060405234801561001057600080fd5b50600436106101215760003560e01c806370a08231116100ad57806395d89b411161007157806395d89b41146102f8578063a457c2d714610316578063a9059cbb14610346578063dd62ed3e14610376578063f2fde38b146103a657610121565b806370a0823114610266578063715018a6146102965780637a1395aa146102a0578063893d20e8146102bc5780638da5cb5b146102da57610121565b806323b872dd116100f457806323b872dd146101b0578063253279ad146101e0578063310c6226146101fc578063313ce56714610218578063395093511461023657610121565b806306fdde0314610126578063095ea7b3146101445780630ca1c5c91461017457806318160ddd14610192575b600080fd5b61012e6103c2565b60405161013b91906114d0565b60405180910390f35b61015e6004803603810190610159919061159a565b610454565b60405161016b91906115f5565b60405180910390f35b61017c610477565b604051610189919061161f565b60405180910390f35b61019a610481565b6040516101a7919061161f565b60405180910390f35b6101ca60048036038101906101c5919061163a565b61048b565b6040516101d791906115f5565b60405180910390f35b6101fa60048036038101906101f591906117fb565b6104ba565b005b6102166004803603810190610211919061189a565b610695565b005b6102206106e7565b60405161022d91906118d6565b60405180910390f35b610250600480360381019061024b919061159a565b6106fe565b60405161025d91906115f5565b60405180910390f35b610280600480360381019061027b91906118f1565b610735565b60405161028d919061161f565b60405180910390f35b61029e61077e565b005b6102ba60048036038101906102b5919061191e565b610792565b005b6102c46107b8565b6040516102d1919061195a565b60405180910390f35b6102e26107e2565b6040516102ef919061195a565b60405180910390f35b61030061080c565b60405161030d91906114d0565b60405180910390f35b610330600480360381019061032b919061159a565b61089e565b60405161033d91906115f5565b60405180910390f35b610360600480360381019061035b919061159a565b610915565b60405161036d91906115f5565b60405180910390f35b610390600480360381019061038b9190611975565b610938565b60405161039d919061161f565b60405180910390f35b6103c060048036038101906103bb91906118f1565b6109bf565b005b6060603680546103d1906119e4565b80601f01602080910402602001604051908101604052809291908181526020018280546103fd906119e4565b801561044a5780601f1061041f5761010080835404028352916020019161044a565b820191906000526020600020905b81548152906001019060200180831161042d57829003601f168201915b5050505050905090565b60008061045f610a43565b905061046c818585610a4b565b600191505092915050565b6000609854905090565b6000603554905090565b600080610496610a43565b90506104a3858285610c16565b6104ae858585610ca2565b60019150509392505050565b60008060019054906101000a900460ff161590508080156104eb5750600160008054906101000a900460ff1660ff16105b8061051857506104fa30610f1d565b1580156105175750600160008054906101000a900460ff1660ff16145b5b610557576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161054e90611a88565b60405180910390fd5b60016000806101000a81548160ff021916908360ff1602179055508015610594576001600060016101000a81548160ff0219169083151502179055505b61059e8585610f40565b6105a6610f9d565b33609760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082609760146101000a81548160ff021916908360ff16021790555061062e609760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683610ff6565b81609881905550801561068e5760008060016101000a81548160ff0219169083151502179055507f7f26b83ff96e1f2b6a682f133852f6798a09c465da95921460cefb384740249860016040516106859190611aed565b60405180910390a15b5050505050565b61069d61114e565b6106c9609760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1682610ff6565b6106de816098546111cc90919063ffffffff16565b60988190555050565b6000609760149054906101000a900460ff16905090565b600080610709610a43565b905061072a81858561071b8589610938565b6107259190611b37565b610a4b565b600191505092915050565b6000603360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b61078661114e565b61079060006111e2565b565b61079a61114e565b80609760146101000a81548160ff021916908360ff16021790555050565b6000609760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000606560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60606037805461081b906119e4565b80601f0160208091040260200160405190810160405280929190818152602001828054610847906119e4565b80156108945780601f1061086957610100808354040283529160200191610894565b820191906000526020600020905b81548152906001019060200180831161087757829003601f168201915b5050505050905090565b6000806108a9610a43565b905060006108b78286610938565b9050838110156108fc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108f390611bff565b60405180910390fd5b6109098286868403610a4b565b60019250505092915050565b600080610920610a43565b905061092d818585610ca2565b600191505092915050565b6000603460008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6109c761114e565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610a37576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a2e90611c91565b60405180910390fd5b610a40816111e2565b50565b600033905090565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610abb576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ab290611d23565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610b2b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b2290611db5565b60405180910390fd5b80603460008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92583604051610c09919061161f565b60405180910390a3505050565b6000610c228484610938565b90507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8114610c9c5781811015610c8e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c8590611e21565b60405180910390fd5b610c9b8484848403610a4b565b5b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610d12576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d0990611eb3565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610d82576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d7990611f45565b60405180910390fd5b610d8d8383836112a8565b6000603360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905081811015610e14576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610e0b90611fd7565b60405180910390fd5b818103603360008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555081603360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef84604051610f04919061161f565b60405180910390a3610f178484846112ad565b50505050565b6000808273ffffffffffffffffffffffffffffffffffffffff163b119050919050565b600060019054906101000a900460ff16610f8f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f8690612069565b60405180910390fd5b610f9982826112b2565b5050565b600060019054906101000a900460ff16610fec576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610fe390612069565b60405180910390fd5b610ff4611333565b565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415611066576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161105d906120d5565b60405180910390fd5b611072600083836112a8565b80603560008282546110849190611b37565b9250508190555080603360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef83604051611136919061161f565b60405180910390a361114a600083836112ad565b5050565b611156610a43565b73ffffffffffffffffffffffffffffffffffffffff166111746107e2565b73ffffffffffffffffffffffffffffffffffffffff16146111ca576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016111c190612141565b60405180910390fd5b565b600081836111da9190611b37565b905092915050565b6000606560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081606560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b505050565b505050565b600060019054906101000a900460ff16611301576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016112f890612069565b60405180910390fd5b8160369080519060200190611317929190611394565b50806037908051906020019061132e929190611394565b505050565b600060019054906101000a900460ff16611382576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161137990612069565b60405180910390fd5b61139261138d610a43565b6111e2565b565b8280546113a0906119e4565b90600052602060002090601f0160209004810192826113c25760008555611409565b82601f106113db57805160ff1916838001178555611409565b82800160010185558215611409579182015b828111156114085782518255916020019190600101906113ed565b5b509050611416919061141a565b5090565b5b8082111561143357600081600090555060010161141b565b5090565b600081519050919050565b600082825260208201905092915050565b60005b83811015611471578082015181840152602081019050611456565b83811115611480576000848401525b50505050565b6000601f19601f8301169050919050565b60006114a282611437565b6114ac8185611442565b93506114bc818560208601611453565b6114c581611486565b840191505092915050565b600060208201905081810360008301526114ea8184611497565b905092915050565b6000604051905090565b600080fd5b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061153182611506565b9050919050565b61154181611526565b811461154c57600080fd5b50565b60008135905061155e81611538565b92915050565b6000819050919050565b61157781611564565b811461158257600080fd5b50565b6000813590506115948161156e565b92915050565b600080604083850312156115b1576115b06114fc565b5b60006115bf8582860161154f565b92505060206115d085828601611585565b9150509250929050565b60008115159050919050565b6115ef816115da565b82525050565b600060208201905061160a60008301846115e6565b92915050565b61161981611564565b82525050565b60006020820190506116346000830184611610565b92915050565b600080600060608486031215611653576116526114fc565b5b60006116618682870161154f565b93505060206116728682870161154f565b925050604061168386828701611585565b9150509250925092565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6116cf82611486565b810181811067ffffffffffffffff821117156116ee576116ed611697565b5b80604052505050565b60006117016114f2565b905061170d82826116c6565b919050565b600067ffffffffffffffff82111561172d5761172c611697565b5b61173682611486565b9050602081019050919050565b82818337600083830152505050565b600061176561176084611712565b6116f7565b90508281526020810184848401111561178157611780611692565b5b61178c848285611743565b509392505050565b600082601f8301126117a9576117a861168d565b5b81356117b9848260208601611752565b91505092915050565b600060ff82169050919050565b6117d8816117c2565b81146117e357600080fd5b50565b6000813590506117f5816117cf565b92915050565b60008060008060808587031215611815576118146114fc565b5b600085013567ffffffffffffffff81111561183357611832611501565b5b61183f87828801611794565b945050602085013567ffffffffffffffff8111156118605761185f611501565b5b61186c87828801611794565b935050604061187d878288016117e6565b925050606061188e87828801611585565b91505092959194509250565b6000602082840312156118b0576118af6114fc565b5b60006118be84828501611585565b91505092915050565b6118d0816117c2565b82525050565b60006020820190506118eb60008301846118c7565b92915050565b600060208284031215611907576119066114fc565b5b60006119158482850161154f565b91505092915050565b600060208284031215611934576119336114fc565b5b6000611942848285016117e6565b91505092915050565b61195481611526565b82525050565b600060208201905061196f600083018461194b565b92915050565b6000806040838503121561198c5761198b6114fc565b5b600061199a8582860161154f565b92505060206119ab8582860161154f565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806119fc57607f821691505b60208210811415611a1057611a0f6119b5565b5b50919050565b7f496e697469616c697a61626c653a20636f6e747261637420697320616c72656160008201527f647920696e697469616c697a6564000000000000000000000000000000000000602082015250565b6000611a72602e83611442565b9150611a7d82611a16565b604082019050919050565b60006020820190508181036000830152611aa181611a65565b9050919050565b6000819050919050565b6000819050919050565b6000611ad7611ad2611acd84611aa8565b611ab2565b6117c2565b9050919050565b611ae781611abc565b82525050565b6000602082019050611b026000830184611ade565b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000611b4282611564565b9150611b4d83611564565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115611b8257611b81611b08565b5b828201905092915050565b7f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f7760008201527f207a65726f000000000000000000000000000000000000000000000000000000602082015250565b6000611be9602583611442565b9150611bf482611b8d565b604082019050919050565b60006020820190508181036000830152611c1881611bdc565b9050919050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b6000611c7b602683611442565b9150611c8682611c1f565b604082019050919050565b60006020820190508181036000830152611caa81611c6e565b9050919050565b7f45524332303a20617070726f76652066726f6d20746865207a65726f2061646460008201527f7265737300000000000000000000000000000000000000000000000000000000602082015250565b6000611d0d602483611442565b9150611d1882611cb1565b604082019050919050565b60006020820190508181036000830152611d3c81611d00565b9050919050565b7f45524332303a20617070726f766520746f20746865207a65726f20616464726560008201527f7373000000000000000000000000000000000000000000000000000000000000602082015250565b6000611d9f602283611442565b9150611daa82611d43565b604082019050919050565b60006020820190508181036000830152611dce81611d92565b9050919050565b7f45524332303a20696e73756666696369656e7420616c6c6f77616e6365000000600082015250565b6000611e0b601d83611442565b9150611e1682611dd5565b602082019050919050565b60006020820190508181036000830152611e3a81611dfe565b9050919050565b7f45524332303a207472616e736665722066726f6d20746865207a65726f20616460008201527f6472657373000000000000000000000000000000000000000000000000000000602082015250565b6000611e9d602583611442565b9150611ea882611e41565b604082019050919050565b60006020820190508181036000830152611ecc81611e90565b9050919050565b7f45524332303a207472616e7366657220746f20746865207a65726f206164647260008201527f6573730000000000000000000000000000000000000000000000000000000000602082015250565b6000611f2f602383611442565b9150611f3a82611ed3565b604082019050919050565b60006020820190508181036000830152611f5e81611f22565b9050919050565b7f45524332303a207472616e7366657220616d6f756e742065786365656473206260008201527f616c616e63650000000000000000000000000000000000000000000000000000602082015250565b6000611fc1602683611442565b9150611fcc82611f65565b604082019050919050565b60006020820190508181036000830152611ff081611fb4565b9050919050565b7f496e697469616c697a61626c653a20636f6e7472616374206973206e6f74206960008201527f6e697469616c697a696e67000000000000000000000000000000000000000000602082015250565b6000612053602b83611442565b915061205e82611ff7565b604082019050919050565b6000602082019050818103600083015261208281612046565b9050919050565b7f45524332303a206d696e7420746f20746865207a65726f206164647265737300600082015250565b60006120bf601f83611442565b91506120ca82612089565b602082019050919050565b600060208201905081810360008301526120ee816120b2565b9050919050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b600061212b602083611442565b9150612136826120f5565b602082019050919050565b6000602082019050818103600083015261215a8161211e565b905091905056fea2646970667358221220ef870b322415a51810c68874eddefd917666b2899a6bf4e5e764de901006e22464736f6c63430008090033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETTOTALMINTED = "getTotalMinted";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_MINTNEWTOKENS = "mintNewTokens";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETDECIMALS = "setDecimals";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event INITIALIZED_EVENT = new Event("Initialized", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected UpgradableTokenTest(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UpgradableTokenTest(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UpgradableTokenTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UpgradableTokenTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<InitializedEventResponse> getInitializedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INITIALIZED_EVENT, transactionReceipt);
        ArrayList<InitializedEventResponse> responses = new ArrayList<InitializedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InitializedEventResponse typedResponse = new InitializedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.version = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InitializedEventResponse> initializedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, InitializedEventResponse>() {
            @Override
            public InitializedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INITIALIZED_EVENT, log);
                InitializedEventResponse typedResponse = new InitializedEventResponse();
                typedResponse.log = log;
                typedResponse.version = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<InitializedEventResponse> initializedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INITIALIZED_EVENT));
        return initializedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getTotalMinted() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALMINTED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String tokenName, String tokenSymbol, BigInteger tokenDecimals, BigInteger initialSupply) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tokenName), 
                new org.web3j.abi.datatypes.Utf8String(tokenSymbol), 
                new org.web3j.abi.datatypes.generated.Uint8(tokenDecimals), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mintNewTokens(BigInteger newTokens) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINTNEWTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newTokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDecimals(BigInteger tokenDecimals) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETDECIMALS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(tokenDecimals)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static UpgradableTokenTest load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UpgradableTokenTest(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UpgradableTokenTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UpgradableTokenTest(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UpgradableTokenTest load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UpgradableTokenTest(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UpgradableTokenTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UpgradableTokenTest(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UpgradableTokenTest> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UpgradableTokenTest.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UpgradableTokenTest> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UpgradableTokenTest.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<UpgradableTokenTest> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UpgradableTokenTest.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UpgradableTokenTest> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UpgradableTokenTest.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class InitializedEventResponse extends BaseEventResponse {
        public BigInteger version;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
