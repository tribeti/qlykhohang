#!/bin/bash
# Script để chạy ứng dụng Quản Lý Thiết Bị

cd "$(dirname "$0")"

# Kiểm tra thư mục bin
if [ ! -d "bin" ]; then
    echo "Biên dịch dự án..."
    javac -d bin src/model/*.java src/controller/*.java src/view/*.java src/util/*.java src/App.java
    if [ $? -ne 0 ]; then
        echo "Lỗi biên dịch!"
        exit 1
    fi
fi

# Chạy ứng dụng
echo "Khởi động ứng dụng..."
java -cp bin App
