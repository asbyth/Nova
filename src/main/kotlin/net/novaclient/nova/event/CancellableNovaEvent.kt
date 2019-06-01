package net.novaclient.nova.event

class CancellableNovaEvent : NovaEvent() {

    var isCancelled: Boolean = false

}