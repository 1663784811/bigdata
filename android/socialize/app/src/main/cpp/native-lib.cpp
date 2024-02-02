#include <jni.h>
#include <string>



extern "C" {
#include <android/native_window_jni.h>
#include <libavcodec/avcodec.h>
#include <libavcodec/defs.h>
#include <libavformat/avformat.h>
#include <libavutil/imgutils.h>
#include <libswscale/swscale.h>

}


extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_socialize_WangyiPlayer_stringFromJNI(JNIEnv *env, jobject) {


    std::string hello = "Hello from C++";

    hello.c_str();

    return env->NewStringUTF(av_version_info());
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_socialize_WangyiPlayer_native_1start(
        JNIEnv *env,
        jobject thiz,
        jstring path_,
        jobject surface
) {
    ANativeWindow *nativeWindow = ANativeWindow_fromSurface(env, surface);

    const char *path = env->GetStringUTFChars(path_, 0);
    // FFmpeg 视频绘制  音频绘制
    // 网络 初始化模块
    avformat_network_init();
    //  总上下文
    AVFormatContext *formatContext = avformat_alloc_context();
    //
    AVDictionary *opts = NULL;
    av_dict_set(&opts, "timeou", "3000000", 0);
    int ret = avformat_open_input(&formatContext, path, NULL, &opts);
    if (ret) {
        return;
    }
    // 视频流
    int vidio_stream_idx = -1;
    avformat_find_stream_info(formatContext, NULL);
    for (int i = 0; i < formatContext->nb_streams; i++) {
        if (formatContext->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
            vidio_stream_idx = i;
            break;
        }
    }

    // 视频流索引
    AVCodecParameters *codecper = formatContext->streams[vidio_stream_idx]->codecpar;

    // 解码H264       ==================================================================
    AVCodec *dec = const_cast<AVCodec *>(avcodec_find_decoder(codecper->codec_id));

    // 解码器的上下文
    AVCodecContext *codecContext = avcodec_alloc_context3(dec);
    // 将解码器参数copy到解码器上下文
    avcodec_parameters_to_context(codecContext, codecper);

    avcodec_open2(codecContext, dec, NULL);

    //===========
    // 解码 yuv数据
    AVPacket *packet = av_packet_alloc();

    SwsContext *swsContext = sws_getContext(
            codecContext->width,codecContext->height,codecContext->pix_fmt,
            codecContext->width,codecContext->height,AV_PIX_FMT_RGBA,
            SWS_BILINEAR,0,0,0);

    av_read_frame(formatContext, packet);

    ANativeWindow_setBuffersGeometry(nativeWindow, codecContext->width, codecContext->height,WINDOW_FORMAT_RGBA_8888);

    ANativeWindow_Buffer outBuffer;
    while (av_read_frame(formatContext, packet) >= 0) {
        avcodec_send_packet(codecContext, packet);
        AVFrame *frame = av_frame_alloc();
        ret = avcodec_receive_frame(codecContext, frame);
        if (ret == AVERROR(EAGAIN)) {
            continue;
        } else if (ret < 0) {
            break;
        }
        // 接收的容器
        uint8_t *dst_data[4];
        // 第一行的首地址
        int dst_linesize[4];
        //
        av_image_alloc(dst_data,dst_linesize,codecContext->width,codecContext->height,AV_PIX_FMT_RGBA,1);
        // 绘制
        sws_scale(swsContext,frame->data,frame->linesize,0,frame->height,dst_data, dst_linesize);


        // ============================= 加锁
        ANativeWindow_lock(nativeWindow, &outBuffer, NULL);

        // 渲染
        uint8_t *src_data = static_cast<uint8_t *>(outBuffer.bits);

        for(int i=0 ; i< outBuffer.height; ++i){
            // 内存copy
//            memccpy();
        }

        //  ============================  解锁


        // AvFrame  ->  yum ->  image  === dest_data   ===> 渲染


    }

}



