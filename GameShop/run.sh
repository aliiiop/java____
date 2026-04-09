#!/bin/bash
echo "🔨 Компиляция..."
javac src/*.java -d .
if [ $? -eq 0 ]; then echo "✅ Запуск..."; java Main; else echo "❌ Ошибка"; fi
