#!/usr/bin/env bash

for input in fixtures/*.input; do
    echo $input
    diff -u <(cat fixtures/$(basename $input .input).expected | sort) \
            <(lein run < $input | sort)
done
