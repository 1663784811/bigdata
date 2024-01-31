cmake_minimum_required(VERSION 3.22.1)

project("socialize")



include_directories(./include)
#设置链接静态库的目录.
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${CMAKE_SOURCE_DIR}/../../../libs/${CMAKE_ANDROID_ARCH_ABI}")

add_library(${CMAKE_PROJECT_NAME} SHARED native-lib.cpp)




target_link_libraries(
        ${CMAKE_PROJECT_NAME}
        avcodec
        avfilter
        avformat
        avutil
        swresample
        swscale
        android
        log
)






