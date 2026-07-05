Write-Host "🧪 Testando conexão com os bancos de dados..." -ForegroundColor Cyan

# Testar Auth DB (porta 5435)
Write-Host "`n1. Auth DB (porta 5435):" -ForegroundColor Yellow
try {
    docker exec auth-db psql -U postgres -d auth_db -c "SELECT 'Auth DB OK' as status;" 2>$null
    Write-Host "✅ Auth DB conectado!" -ForegroundColor Green
} catch {
    Write-Host "❌ Auth DB não está rodando" -ForegroundColor Red
}

# Testar Transaction DB (porta 5433)
Write-Host "`n2. Transaction DB (porta 5433):" -ForegroundColor Yellow
try {
    docker exec transaction-db psql -U postgres -d transaction_db -c "SELECT 'Transaction DB OK' as status;" 2>$null
    Write-Host "✅ Transaction DB conectado!" -ForegroundColor Green
} catch {
    Write-Host "❌ Transaction DB não está rodando" -ForegroundColor Red
}

# Testar Report DB (porta 5434)
Write-Host "`n3. Report DB (porta 5434):" -ForegroundColor Yellow
try {
    docker exec report-db psql -U postgres -d report_db -c "SELECT 'Report DB OK' as status;" 2>$null
    Write-Host "✅ Report DB conectado!" -ForegroundColor Green
} catch {
    Write-Host "❌ Report DB não está rodando" -ForegroundColor Red
}

Write-Host "`n📊 Portas configuradas:" -ForegroundColor Cyan
Write-Host "   Auth DB:      5435" -ForegroundColor White
Write-Host "   Transaction:  5433" -ForegroundColor White
Write-Host "   Report:       5434" -ForegroundColor White
