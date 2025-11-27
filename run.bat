@echo off
REM Script để chạy ứng dụng Quản Lý Thiết Bị
REM

cd /d "%~dp0"

REM Kiểm tra thư mục bin có tồn tại không
if not exist "bin" (
    echo Biên dịch dự án...
    javac -d bin src\model\*.java src\controller\*.java src\view\*.java src\util\*.java src\App.java
    if errorlevel 1 (
        echo Lỗi biên dịch!
        pause
        exit /b 1
    )
)

REM Chạy ứng dụng
echo Khởi động ứng dụng...
java -cp bin App
pause
