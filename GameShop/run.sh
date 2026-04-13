#!/bin/bash
echo "🔨 Компиляция проекта..."
javac src/*.java -d .
if [ $? -eq 0 ]; then
    echo "✅ Компиляция успешна!"
    echo "🚀 Запуск GameShop..."
    echo ""
    java Main
else
    echo "❌ Ошибка компиляции!"
    exit 1
fi
