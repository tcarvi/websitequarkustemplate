FROM ghcr.io/graalvm/graalvm-ce:latest

WORKDIR /

RUN gu install python

RUN gu install llvm-toolchain
RUN export LLVM_TOOLCHAIN=$(lli --print-toolchain-path)

COPY wasm/c/hello.c /hello.c

RUN /opt/graalvm-ce-java11-21.2.0/languages/llvm/native/bin/clang hello.c -o hello
RUN lli hello

RUN gu install wasm
COPY libs/emsdk/ /emsdk/
RUN ls -la /emsdk
COPY wasm/c/hello.c /emsdk/hello.c
WORKDIR /emsdk

# Fetch the latest version of the emsdk (not needed the first time you clone)
# git pull

# Download and install the latest SDK tools.
RUN ./emsdk install latest

# Make the "latest" SDK "active" for the current user. (writes .emscripten file)
RUN ./emsdk activate latest

# Activate PATH and other environment variables in the current terminal
# RUN source ./emsdk_env.sh
RUN ls
RUN emcc -o hello.wasm hello.c
RUN wasm --Builtins=wasi_snapshot_preview1 hello.wasm