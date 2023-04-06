#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

java -cp "$SCRIPT_DIR:$SCRIPT_DIR/../lib" com.gomilkyway.cmd.AdariCmd "$@"