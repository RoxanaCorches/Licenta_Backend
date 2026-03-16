package com.example.backend_springboot.blockchain.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
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
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class Marketplace extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_CANCEL = "cancel";

    public static final String FUNC_CHECKIN = "checkIn";

    public static final String FUNC_CHECKOUT = "checkout";

    public static final String FUNC_DELISTNFTFROMMARKETPLACE = "delistNftFromMarketplace";

    public static final String FUNC_HOURSTOCHECKIN = "hoursToCheckIn";

    public static final String FUNC_ISAVAILABLEFORRENT = "isAvailableForRent";

    public static final String FUNC_ISNFTLISTED = "isNftListed";

    public static final String FUNC_LISTNFTONMARKETPLACE = "listNftOnMarketplace";

    public static final String FUNC_OWNERMARKETPLACEADDRESS = "ownerMarketplaceAddress";

    public static final String FUNC_OWNERNFT = "ownerNft";

    public static final String FUNC_PRICEPERDAY = "pricePerDay";

    public static final String FUNC_PROPERTIES = "properties";

    public static final String FUNC_RENT = "rent";

    public static final String FUNC_RENTALTOKENID = "rentalTokenId";

    public static final String FUNC_UPDATEHOURSTOCHECKIN = "updateHoursToCheckIn";

    public static final String FUNC_UPDATEPRICEPERDAY = "updatePricePerDay";

    public static final Event NFTCANCEL_EVENT = new Event("NFTCancel", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NFTCHECKIN_EVENT = new Event("NFTCheckIn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NFTCHECKOUT_EVENT = new Event("NFTCheckOut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NFTDELISTED_EVENT = new Event("NFTDelisted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NFTLISTED_EVENT = new Event("NFTListed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NFTRENTED_EVENT = new Event("NFTRented", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event UPDATEHOURS_EVENT = new Event("updateHours", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPDATEPRICE_EVENT = new Event("updatePrice", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected Marketplace(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Marketplace(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Marketplace(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Marketplace(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> cancel(BigInteger tokenId) {
        final Function function = new Function(
                FUNC_CANCEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> checkIn(BigInteger tokenId) {
        final Function function = new Function(
                FUNC_CHECKIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> checkout(BigInteger tokenId) {
        final Function function = new Function(
                FUNC_CHECKOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> delistNftFromMarketplace(BigInteger tokenId) {
        final Function function = new Function(
                FUNC_DELISTNFTFROMMARKETPLACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> hoursToCheckIn(BigInteger param0) {
        final Function function = new Function(FUNC_HOURSTOCHECKIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isAvailableForRent(BigInteger tokenId, BigInteger startDate, BigInteger endDate) {
        final Function function = new Function(FUNC_ISAVAILABLEFORRENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(startDate), 
                new org.web3j.abi.datatypes.generated.Uint256(endDate)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isNftListed(BigInteger param0) {
        final Function function = new Function(FUNC_ISNFTLISTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> listNftOnMarketplace(BigInteger tokenId, BigInteger price, BigInteger hoursForCheckIn) {
        final Function function = new Function(
                FUNC_LISTNFTONMARKETPLACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.generated.Uint256(hoursForCheckIn)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> ownerMarketplaceAddress() {
        final Function function = new Function(FUNC_OWNERMARKETPLACEADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerNft(BigInteger param0) {
        final Function function = new Function(FUNC_OWNERNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> pricePerDay(BigInteger param0) {
        final Function function = new Function(FUNC_PRICEPERDAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> properties() {
        final Function function = new Function(FUNC_PROPERTIES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> rent(BigInteger tokenId, BigInteger startDate, BigInteger endDate, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_RENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(startDate), 
                new org.web3j.abi.datatypes.generated.Uint256(endDate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> rentalTokenId(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_RENTALTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateHoursToCheckIn(BigInteger tokenId, BigInteger newHours) {
        final Function function = new Function(
                FUNC_UPDATEHOURSTOCHECKIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(newHours)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updatePricePerDay(BigInteger tokenId, BigInteger newPrice) {
        final Function function = new Function(
                FUNC_UPDATEPRICEPERDAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(newPrice)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static List<NFTCancelEventResponse> getNFTCancelEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NFTCANCEL_EVENT, transactionReceipt);
        ArrayList<NFTCancelEventResponse> responses = new ArrayList<NFTCancelEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTCancelEventResponse typedResponse = new NFTCancelEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NFTCancelEventResponse getNFTCancelEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NFTCANCEL_EVENT, log);
        NFTCancelEventResponse typedResponse = new NFTCancelEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NFTCancelEventResponse> nFTCancelEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNFTCancelEventFromLog(log));
    }

    public Flowable<NFTCancelEventResponse> nFTCancelEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTCANCEL_EVENT));
        return nFTCancelEventFlowable(filter);
    }

    public static List<NFTCheckInEventResponse> getNFTCheckInEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NFTCHECKIN_EVENT, transactionReceipt);
        ArrayList<NFTCheckInEventResponse> responses = new ArrayList<NFTCheckInEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTCheckInEventResponse typedResponse = new NFTCheckInEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NFTCheckInEventResponse getNFTCheckInEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NFTCHECKIN_EVENT, log);
        NFTCheckInEventResponse typedResponse = new NFTCheckInEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NFTCheckInEventResponse> nFTCheckInEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNFTCheckInEventFromLog(log));
    }

    public Flowable<NFTCheckInEventResponse> nFTCheckInEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTCHECKIN_EVENT));
        return nFTCheckInEventFlowable(filter);
    }

    public static List<NFTCheckOutEventResponse> getNFTCheckOutEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NFTCHECKOUT_EVENT, transactionReceipt);
        ArrayList<NFTCheckOutEventResponse> responses = new ArrayList<NFTCheckOutEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTCheckOutEventResponse typedResponse = new NFTCheckOutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NFTCheckOutEventResponse getNFTCheckOutEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NFTCHECKOUT_EVENT, log);
        NFTCheckOutEventResponse typedResponse = new NFTCheckOutEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NFTCheckOutEventResponse> nFTCheckOutEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNFTCheckOutEventFromLog(log));
    }

    public Flowable<NFTCheckOutEventResponse> nFTCheckOutEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTCHECKOUT_EVENT));
        return nFTCheckOutEventFlowable(filter);
    }

    public static List<NFTDelistedEventResponse> getNFTDelistedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NFTDELISTED_EVENT, transactionReceipt);
        ArrayList<NFTDelistedEventResponse> responses = new ArrayList<NFTDelistedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTDelistedEventResponse typedResponse = new NFTDelistedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NFTDelistedEventResponse getNFTDelistedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NFTDELISTED_EVENT, log);
        NFTDelistedEventResponse typedResponse = new NFTDelistedEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<NFTDelistedEventResponse> nFTDelistedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNFTDelistedEventFromLog(log));
    }

    public Flowable<NFTDelistedEventResponse> nFTDelistedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTDELISTED_EVENT));
        return nFTDelistedEventFlowable(filter);
    }

    public static List<NFTListedEventResponse> getNFTListedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NFTLISTED_EVENT, transactionReceipt);
        ArrayList<NFTListedEventResponse> responses = new ArrayList<NFTListedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTListedEventResponse typedResponse = new NFTListedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NFTListedEventResponse getNFTListedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NFTLISTED_EVENT, log);
        NFTListedEventResponse typedResponse = new NFTListedEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NFTListedEventResponse> nFTListedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNFTListedEventFromLog(log));
    }

    public Flowable<NFTListedEventResponse> nFTListedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTLISTED_EVENT));
        return nFTListedEventFlowable(filter);
    }

    public static List<NFTRentedEventResponse> getNFTRentedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NFTRENTED_EVENT, transactionReceipt);
        ArrayList<NFTRentedEventResponse> responses = new ArrayList<NFTRentedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTRentedEventResponse typedResponse = new NFTRentedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.numberOfDays = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.totalPrice = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NFTRentedEventResponse getNFTRentedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NFTRENTED_EVENT, log);
        NFTRentedEventResponse typedResponse = new NFTRentedEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.renter = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.numberOfDays = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.totalPrice = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<NFTRentedEventResponse> nFTRentedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNFTRentedEventFromLog(log));
    }

    public Flowable<NFTRentedEventResponse> nFTRentedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTRENTED_EVENT));
        return nFTRentedEventFlowable(filter);
    }

    public static List<UpdateHoursEventResponse> getUpdateHoursEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UPDATEHOURS_EVENT, transactionReceipt);
        ArrayList<UpdateHoursEventResponse> responses = new ArrayList<UpdateHoursEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UpdateHoursEventResponse typedResponse = new UpdateHoursEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newHours = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UpdateHoursEventResponse getUpdateHoursEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UPDATEHOURS_EVENT, log);
        UpdateHoursEventResponse typedResponse = new UpdateHoursEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newHours = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<UpdateHoursEventResponse> updateHoursEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUpdateHoursEventFromLog(log));
    }

    public Flowable<UpdateHoursEventResponse> updateHoursEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATEHOURS_EVENT));
        return updateHoursEventFlowable(filter);
    }

    public static List<UpdatePriceEventResponse> getUpdatePriceEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UPDATEPRICE_EVENT, transactionReceipt);
        ArrayList<UpdatePriceEventResponse> responses = new ArrayList<UpdatePriceEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UpdatePriceEventResponse typedResponse = new UpdatePriceEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newPrice = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UpdatePriceEventResponse getUpdatePriceEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UPDATEPRICE_EVENT, log);
        UpdatePriceEventResponse typedResponse = new UpdatePriceEventResponse();
        typedResponse.log = log;
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newPrice = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<UpdatePriceEventResponse> updatePriceEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUpdatePriceEventFromLog(log));
    }

    public Flowable<UpdatePriceEventResponse> updatePriceEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATEPRICE_EVENT));
        return updatePriceEventFlowable(filter);
    }

    @Deprecated
    public static Marketplace load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Marketplace(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Marketplace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Marketplace(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Marketplace load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Marketplace(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Marketplace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Marketplace(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class NFTCancelEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String renter;

        public BigInteger startDate;

        public BigInteger endDate;
    }

    public static class NFTCheckInEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String renter;

        public BigInteger startDate;

        public BigInteger endDate;
    }

    public static class NFTCheckOutEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String renter;

        public BigInteger startDate;

        public BigInteger endDate;
    }

    public static class NFTDelistedEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger tokenId;
    }

    public static class NFTListedEventResponse extends BaseEventResponse {
        public String owner;

        public String newOwner;

        public BigInteger tokenId;

        public BigInteger price;
    }

    public static class NFTRentedEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String renter;

        public BigInteger numberOfDays;

        public BigInteger totalPrice;

        public BigInteger startDate;

        public BigInteger endDate;
    }

    public static class UpdateHoursEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public BigInteger newHours;
    }

    public static class UpdatePriceEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public BigInteger newPrice;
    }
}
