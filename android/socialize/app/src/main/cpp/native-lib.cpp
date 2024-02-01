#include <jni.h>
#include <string>


extern "C" {
#include <libavcodec/avcodec.h>
#include <libavcodec/defs.h>
#include <libavformat/avformat.h>
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
        jstring path,
        jobject surface
) {
    int networkInit = avformat_network_init();
    AVFormatContext *formatContext = avformat_alloc_context();


}