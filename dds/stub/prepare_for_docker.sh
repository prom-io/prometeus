#!/bin/sh

set -e

path_to_jar=$1
shift                 #(1)
app_modules=$*        #(2)

dir=$(dirname "${path_to_jar}")
jar_name=$(basename "${path_to_jar}")

mkdir -p "${dir}/docker-dist" && cd "${dir}/docker-dist"

jar -xf ../"${jar_name}"

if [ -n "${app_modules}" ]; then #(3)
    mkdir app-lib

    for i in ${app_modules}; do
        mv "BOOT-INF/lib/$i"* app-lib    #(4)
    done
fi
