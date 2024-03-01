package com.consult_app.demo.config.videocall;

import org.kurento.client.KurentoClient;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;

/**
 * Media Pipeline (WebRTC endpoints, i.e. Kurento Media Elements) and
 * connections for the 1 to 1
 * video communication.
 * 
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @since 4.3.1
 */
public class CallMediaPipeline {

    private MediaPipeline pipeline;
    private WebRtcEndpoint callerWebRtcEp;
    private WebRtcEndpoint calleeWebRtcEp;

    public CallMediaPipeline(KurentoClient kurento) {
        try {
            this.pipeline = kurento.createMediaPipeline();
            this.callerWebRtcEp = new WebRtcEndpoint.Builder(pipeline).build();
            this.calleeWebRtcEp = new WebRtcEndpoint.Builder(pipeline).build();

            this.callerWebRtcEp.connect(this.calleeWebRtcEp);
            this.calleeWebRtcEp.connect(this.callerWebRtcEp);
        } catch (Throwable t) {
            if (this.pipeline != null) {
                pipeline.release();
            }
        }
    }

    public String generateSdpAnswerForCaller(String sdpOffer) {
        return callerWebRtcEp.processOffer(sdpOffer);
    }

    public String generateSdpAnswerForCallee(String sdpOffer) {
        return calleeWebRtcEp.processOffer(sdpOffer);
    }

    public void release() {
        if (pipeline != null) {
            pipeline.release();
        }
    }

    public WebRtcEndpoint getCallerWebRtcEp() {
        return callerWebRtcEp;
    }

    public WebRtcEndpoint getCalleeWebRtcEp() {
        return calleeWebRtcEp;
    }

}
