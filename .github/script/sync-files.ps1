# PowerShell script to sync changed files to sibling directories

# Get current directory
$currentDir = Get-Location

# Get parent directory
$parentDir = Split-Path -Parent $currentDir

# Get all files except .java files
Push-Location $currentDir
$filesToSync = Get-ChildItem -Path . -Recurse -File | 
    Where-Object { $_.Extension -eq ".yml" } | 
    ForEach-Object { 
        $relativePath = (Resolve-Path -Relative $_.FullName)
        # Remove .\ or ./ prefix but preserve filenames starting with dot
        if ($relativePath.StartsWith(".\")) {
            $relativePath.Substring(2)
        } elseif ($relativePath.StartsWith("./")) {
            $relativePath.Substring(2)
        } else {
            $relativePath
        }
    }
Pop-Location

Write-Host "Found $($filesToSync.Count) files to sync (excluding .java files)" -ForegroundColor Cyan

# Find sibling directories matching pattern
$pattern = "baraksu-class-2026-classroom-01-unit04*"
$targetDirs = Get-ChildItem -Path $parentDir -Directory -Filter $pattern | Where-Object { $_.FullName -ne $currentDir }

Write-Host "Found $($targetDirs.Count) target directories matching pattern: $pattern" -ForegroundColor Cyan
Write-Host ""

foreach ($targetDir in $targetDirs) {
    Write-Host "Syncing to: $($targetDir.Name)" -ForegroundColor Yellow
    
    foreach ($file in $filesToSync) {
        $sourcePath = Join-Path $currentDir $file
        $targetPath = Join-Path $targetDir.FullName $file
        
        if (Test-Path $sourcePath) {
            # Create target directory if it doesn't exist
            $targetFileDir = Split-Path -Parent $targetPath
            if (-not (Test-Path $targetFileDir)) {
                New-Item -ItemType Directory -Path $targetFileDir -Force | Out-Null
            }
            
            # Copy file
            Copy-Item -Path $sourcePath -Destination $targetPath -Force
            Write-Host "  ✓ Copied: $file" -ForegroundColor Green
        } else {
            Write-Host "  ✗ Source not found: $file" -ForegroundColor Red
        }
    }
    Write-Host ""
}

Write-Host "Sync complete!" -ForegroundColor Cyan
