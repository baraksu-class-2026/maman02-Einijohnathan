# Change to GitHub directory
Set-Location "C:\Users\bsuberri\OneDrive - NI\Documents\GitHub"

# Load user dictionary from JSON
$userDictPath = Join-Path $PSScriptRoot 'users_dictionary.json'
$userDictionary = Get-Content -Path $userDictPath -Raw | ConvertFrom-Json

# List of client repositories

$unit = 'unit04-test01'
$clientRepo = ''

# $users = @(
#             'eitanmizlish'

#         )

$users = @(
    'baraksu-teacher',
    'ArielMeyer1',
    'arielsperetz-web',
    'Daniel-Behar-blip',
    'dvirshg',
    'Einijohnathan',
    'Eitan1245',
    'eitand245-spec',
    'eitanmizlish',
    'Elkana-zarsky',
    'eyaleis',
    'eytan1432',
    'i5am5cool',
    'ishayhoft-arch',
    'kosloyair-cyber',
    'MatanH5',
    'Moshe-Halpern',
    'muli122123',
    'naamankoosz-netizen',
    'nevo1218',
    'Noamizaksohn',
    'ronrabi20',
    'roniG1951',
    'Sapir1913',
    'yehonatan-fisher',
    'yehonatan351-dev',
    'Yonatansaghi',
    'yonathanklein2010-droid',
    'Yoavpan',
    'YotamOphir',
    'Yovel-sch',
    'yakir14124'
)

function Update-Repos {
    param (
        [string]$unit,
        [array]$users
    )
    
    # Iterate over each repository
    foreach ($user in $users) {

        Write-Host "Processing repository: $clientRepo" -ForegroundColor Cyan

        $clientRepo = $unit + '-' + $user
        
        if (-not (Test-Path $clientRepo)) {
            
            git clone  https://baraksu-teacher@github.com/baraksu-class-2026/$clientRepo.git
            
            if (-not (Test-Path $clientRepo)) {
                continue
            }

        }

        Set-Location $clientRepo

        git pull

        $remotes = git remote
        
        if ($remotes -notcontains 'teacher') {
            git remote add teacher https://baraksu-teacher@github.com/baraksu-class-2026/baraksu-class-2026-classroom-01-$unit
        }

        git fetch teacher main

        git merge teacher/main


        


        git push


        Set-Location ".."
    }
}

function Check-ReposDoesExist {
    param (
        [string]$unit,
        [array]$users
    )
    
    Write-Host "The following users haven't started task $unit" -ForegroundColor Yellow
    
    # Iterate over each repository
    foreach ($user in $users) {

        $clientRepo = $unit + '-' + $user
        
        # Check if repository exists on GitHub server
        $repoUrl = "https://baraksu-teacher@github.com/baraksu-class-2026/$clientRepo.git"
        $repoExists = git ls-remote $repoUrl 2>&1
        
        if ($LASTEXITCODE -ne 0) {
            $userName = $userDictionary."@$user"
            if ($userName) {
                $reversedName = -join $userName[-1..-$userName.Length]
                Write-Host "$reversedName" -ForegroundColor Red
            } else {
                Write-Host "@$user" -ForegroundColor Red
            }
        }
    }
}


function Update-Secrets {
    param (
        [string]$unit,
        [array]$users
    )

    # API key value from environment variable
    $apiKey = $env:OPENAI_API_KEY
                
               
    if (-not $apiKey) {
            Write-Host "$user : OPENAI_API_KEY environment variable not set. Skipping." -ForegroundColor Red
            Set-Location ".."
            return
    }
                
    
    # Iterate over each repository
    foreach ($user in $users) {

        Write-Host "Processing repository: $clientRepo" -ForegroundColor Cyan

        $clientRepo = $unit + '-' + $user
        
        if (-not (Test-Path $clientRepo)) {
            
            git clone  https://baraksu-teacher@github.com/baraksu-class-2026/$clientRepo.git
            
            if (-not (Test-Path $clientRepo)) {
                continue
            }

        }

        Set-Location $clientRepo

        # Check if OPENAI_API_KEY secret exists
        $secretList = gh secret list -R baraksu-class-2026/$clientRepo 2>&1
        if ($LASTEXITCODE -eq 0) {
            $secretExists = $secretList | Select-String -Pattern "OPENAI_API_KEY"
            
            if (-not $secretExists) {
                
              
                # Add the secret
                $apiKey | gh secret set OPENAI_API_KEY -R baraksu-class-2026/$clientRepo
                
                if ($LASTEXITCODE -eq 0) {
                    Write-Host "$user : OPENAI_API_KEY secret added successfully." -ForegroundColor Yellow
                } else {
                    Write-Host "$user : Failed to add OPENAI_API_KEY secret." -ForegroundColor Red
                }
            } else {
                Write-Host "$user : OPENAI_API_KEY secret already exists." -ForegroundColor Green
            }
        } else {
            Write-Host "$user : Failed to list secrets. Error: $secretList" -ForegroundColor Red
            Write-Host "         You may not have admin access to this repository." -ForegroundColor Yellow
        }


        Set-Location ".."
    }
}


# Call the function
Update-Secrets -unit $unit -users $users
#Update-Repos -unit $unit -users $users
#Check-ReposDoesExist -unit $unit -users $users
