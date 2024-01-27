cmake_minimum_required(VERSION 3.4.1)


file(GLOB SOURCE src/main/cpp/*.cpp)



# Sets the name of the library.
add_library(${CMAKE_PROJECT_NAME} SHARED ${SOURCE})

# Sets the name of the path variable.
find_library(log-lib log)

include_directories(src/main/cpp/include)

set(my_lib_path  ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI})
set(CMAKE_CXX_FLAGS  "${CMAKE_CXX_FLAGS} -L${my_lib_path}")


# Specifies the target library.
target_link_libraries(
        ${CMAKE_PROJECT_NAME}
        avfilter
        avformat
        avcodec
        avutil
        swresample
        swscale
        ${log-lib}
        z
        OpenSLES
        android
        )