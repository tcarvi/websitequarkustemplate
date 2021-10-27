####
# This Dockerfile is used in order to build a container that runs the Quarkus application in native (no JVM) mode
#
# Before building the container image run:
#
# ./mvnw package -Pnative
#
# Then, build the image with:
#
# docker build -f src/main/docker/Dockerfile.native -t quarkus/code-with-quarkus .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 quarkus/code-with-quarkus
#
###
FROM ghcr.io/graalvm/graalvm-ce:latest

WORKDIR /

RUN gu install native-image

RUN gu install python
RUN graalpython -m venv /gpython
RUN source /gpython/bin/activate

RUN gu install llvm-toolchain
RUN export LLVM_TOOLCHAIN=$(lli --print-toolchain-path)

COPY src/main/wasm/c/cScriptInButton.c /cScriptInButton.c
COPY src/main/wasm/cpp/cppScriptInButton.cpp /cppScriptInButton.cpp
COPY src/main/wasm/go/goScriptInButton.go /goScriptInButton.go
COPY src/main/wasm/kotlin/kotlinScriptInButton.kt /kotlinScriptInButton.kt
COPY src/main/wasm/rust/rustScriptInButton.rs /rustScriptInButton.rs
COPY src/main/wasm/swift/swiftScriptInButton.swift /swiftScriptInButton.swift


RUN /opt/graalvm-ce-java11-21.2.0/languages/llvm/native/bin/clang cScriptInButton.c -o cScriptInButton
RUN lli cScriptInButton

RUN gu install wasm
COPY libs/emsdk/ /emsdk/
RUN ls -la /emsdk
COPY src/main/wasm/c/cScriptInButton.c /emsdk/cScriptInButton.c
WORKDIR /emsdk

# Fetch the latest version of the emsdk (not needed the first time you clone)
# git pull

# Download and install the latest SDK tools.
# RUN ./emsdk install latest

# Make the "latest" SDK "active" for the current user. (writes .emscripten file)
# RUN ./emsdk activate latest

# Activate PATH and other environment variables in the current terminal
# RUN source ./emsdk_env.sh
RUN ls
# RUN emcc -o cScriptInButton.wasm cScriptInButton.c
# RUN wasm --Builtins=wasi_snapshot_preview1 cScriptInButton.wasm