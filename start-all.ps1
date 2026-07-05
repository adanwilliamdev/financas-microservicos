Write-Host "🚀 Iniciando Microserviços Finanças..." -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan

# Compilar todos os serviços
Write-Host "`n📦 Compilando todos os serviços..." -ForegroundColor Yellow
mvn clean install -DskipTests

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Erro na compilação. Verifique os erros acima." -ForegroundColor Red
    exit 1
}

Write-Host "✅ Compilação concluída!" -ForegroundColor Green

# Iniciar Service Registry
Write-Host "`n📦 Iniciando Service Registry (porta 8761)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\Projetos\financas-microservicos\service-registry; Write-Host '🔄 Service Registry iniciando...' -ForegroundColor Cyan; mvn spring-boot:run"

Start-Sleep -Seconds 15

# Iniciar Auth Service
Write-Host "`n📦 Iniciando Auth Service (porta 8081)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\Projetos\financas-microservicos\auth-service; Write-Host '🔄 Auth Service iniciando...' -ForegroundColor Cyan; mvn spring-boot:run"

Start-Sleep -Seconds 10

# Iniciar Transaction Service
Write-Host "`n📦 Iniciando Transaction Service (porta 8082)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\Projetos\financas-microservicos\transaction-service; Write-Host '🔄 Transaction Service iniciando...' -ForegroundColor Cyan; mvn spring-boot:run"

Start-Sleep -Seconds 10

# Iniciar Report Service
Write-Host "`n📦 Iniciando Report Service (porta 8083)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\Projetos\financas-microservicos\report-service; Write-Host '🔄 Report Service iniciando...' -ForegroundColor Cyan; mvn spring-boot:run"

Start-Sleep -Seconds 10

# Iniciar API Gateway
Write-Host "`n📦 Iniciando API Gateway (porta 8080)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\Projetos\financas-microservicos\api-gateway; Write-Host '🔄 API Gateway iniciando...' -ForegroundColor Cyan; mvn spring-boot:run"

Write-Host "`n✅ Todos os serviços iniciados!" -ForegroundColor Green
Write-Host "`n📍 URLs:"
Write-Host "   Service Registry: http://localhost:8761" -ForegroundColor Cyan
Write-Host "   API Gateway:      http://localhost:8080" -ForegroundColor Cyan
Write-Host "   Auth Service:     http://localhost:8081" -ForegroundColor Cyan
Write-Host "   Transaction:      http://localhost:8082" -ForegroundColor Cyan
Write-Host "   Report Service:   http://localhost:8083" -ForegroundColor Cyan
Write-Host ""
Write-Host "⏳ Aguarde alguns segundos para todos os serviços iniciarem completamente." -ForegroundColor Yellow
