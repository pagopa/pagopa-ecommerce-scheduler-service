package it.pagopa.transactions.documents;

import it.pagopa.transactions.utils.TransactionEventCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "eventstore")
@NoArgsConstructor
public final class TransactionAuthorizationRequestedEvent extends TransactionEvent<TransactionAuthorizationRequestData> {
    public TransactionAuthorizationRequestedEvent(String transactionId, String rptId, String paymentToken, TransactionAuthorizationRequestData data) {
        super(transactionId, rptId, paymentToken, TransactionEventCode.TRANSACTION_AUTHORIZATION_REQUESTED_EVENT, data);
    }
}
