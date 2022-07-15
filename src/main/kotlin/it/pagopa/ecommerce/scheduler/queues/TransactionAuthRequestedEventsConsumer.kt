package it.pagopa.ecommerce.scheduler.queues

import com.azure.spring.messaging.AzureHeaders
import com.azure.spring.messaging.checkpoint.Checkpointer
import com.azure.spring.messaging.storage.queue.core.StorageQueueTemplate
import com.azure.spring.integration.storage.queue.inbound.StorageQueueMessageSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import org.springframework.context.annotation.Bean

@Service
class TransactionAuthRequestedEventsConsumer() {

    var logger: Logger = LoggerFactory.getLogger(TransactionAuthRequestedEventsConsumer::class.java)
    
    @ServiceActivator(inputChannel = "transactionauthrequestedchannel")
    fun messageReceiver(@Payload payload: ByteArray, @Header(AzureHeaders.CHECKPOINTER) checkpointer: Checkpointer) {
        val message = String(payload)
        checkpointer.success().doOnSuccess({
            logger.info("Message '{}' successfully checkpointed", message)
        })
        .block()
    }
}
