#include <jni.h>
#include <string>

extern "C" {
#include <libavcodec/avcodec.h>
#include <libavcodec/defs.h>

}


extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_socialize_MainActivity_stringFromJNI(JNIEnv *env, jobject) {


    std::string hello = "Hello from C++";

    hello.c_str();

    return env->NewStringUTF(av_version_info());
}

