Write-Host "🔧 Recriando bancos de dados com portas corretas..." -ForegroundColor Cyan

# Auth DB - porta 5435
Write-Host "`n📦 Auth DB (porta 5435)..." -ForegroundColor Yellow
docker stop auth-db 2>$null
docker rm auth-db 2>$null
docker run -d --name auth-db -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=9857Dudu -p 5435:5432 postgres:15-alpine
Start-Sleep -Seconds 10
docker exec -it auth-db psql -U postgres -c "CREATE DATABASE auth_db;" 2>$null
Write-Host "✅ Auth DB pronto!" -ForegroundColor Green

# Transaction DB - porta 5433
Write-Host "`n📦 Transaction DB (porta 5433)..." -ForegroundColor Yellow
docker stop transaction-db 2>$null
docker rm transaction-db 2>$null
docker run -d --name transaction-db -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=9857Dudu -p 5433:5432 postgres:15-alpine
Start-Sleep -Seconds 10
docker exec -it transaction-db psql -U postgres -c "CREATE DATABASE transaction_db;" 2>$null
Write-Host "✅ Transaction DB pronto!" -ForegroundColor Green

# Report DB - porta 5434
Write-Host "`n📦 Report DB (porta 5434)..." -ForegroundColor Yellow
docker stop report-db 2>$null
docker rm report-db 2>$null
docker run -d --name report-db -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=9857Dudu -p 5434:5432 postgres:15-alpine
Start-Sleep -Seconds 10
docker exec -it report-db psql -U postgres -c "CREATE DATABASE report_db;" 2>$null
Write-Host "✅ Report DB pronto!" -ForegroundColor Green

Write-Host "`n📊 Status dos bancos:" -ForegroundColor Cyan
docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

Write-Host "`n✅ Bancos recriados com sucesso!" -ForegroundColor Green
Write-Host ""
Write-Host "📍 Portas:"
Write-Host "   Auth DB:      5435" -ForegroundColor Cyan
Write-Host "   Transaction:  5433" -ForegroundColor Cyan
Write-Host "   Report:       5434" -ForegroundColor Cyan
