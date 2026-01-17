# Change to GitHub directory

$workingDir = "C:\Users\bsuberri\OneDrive - NI\Documents\GitHub"
Set-Location $workingDir

# Load user dictionary from JSON
$userDictPath = Join-Path $PSScriptRoot 'users_dictionary.json'
$userDictionary = Get-Content -Path $userDictPath -Raw | ConvertFrom-Json

# List of client repositories

$unit = 'maman02'
$clientRepo = ''

# $users = @(
#             'dvirshg'

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
    'naamankoosz-netizen',
    'nevo1218',
    'Noamizaksohn',
    'ronrabi20',
    'roniG1951',
    'Sapir1913',
    'yehonatan-fisher',
    'yehonatan351-dev',
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

function Update-MyRepos {
    param (
        [string]$unit,
        [array]$users
    )

    Set-Location "$workingDir\baraksu-class-2026-classroom-01-$unit"

    $remotes = git remote

    # Iterate over each repository
    foreach ($user in $users) {

        $clientRepo = $unit + '-' + $user
       
        Write-Host "Processing repository: $clientRepo" -ForegroundColor Cyan

        $originName = "student-$user"
        
        $repoUrl = "https://baraksu-teacher@github.com/baraksu-class-2026/$clientRepo.git"
        $repoExists = git ls-remote $repoUrl 2>&1
        
        if ($LASTEXITCODE -ne 0) {
            continue
        }
        
        if ($remotes -notcontains $originName) {
            git remote add $originName https://baraksu-teacher@github.com/baraksu-class-2026/$clientRepo.git
        }

        git fetch $originName main

        if ($LASTEXITCODE -ne 0) {
            Write-Host "  Failed to fetch from $originName" -ForegroundColor Red
            continue
        }

        # Check GitHub Actions status
        # $workflowStatus = gh run list -R baraksu-class-2026/$clientRepo --limit 1 --json conclusion --jq '.[0].conclusion' 2>&1
        
        # if ($LASTEXITCODE -ne 0) {
        #     Write-Host "  Failed to check workflow status for $clientRepo" -ForegroundColor Red
        #     continue
        # }

        # if ($workflowStatus -ne "success") {
        #     Write-Host "  Last workflow run for $clientRepo is not successful (status: $workflowStatus). Skipping." -ForegroundColor Yellow
        #     continue
        # }

        # Write-Host "  Last workflow run passed. Proceeding..." -ForegroundColor Green

        foreach ($className in @("HotelUserTester", "HotelRoomUserTester")) {

            $newClassName = Get-UserClassName -className $className  -user $user

            # Check if file exists in remote branch before checkout
            $fileExists = git ls-tree -r --name-only "$originName/main" "$newClassName.java" 2>&1
            
            if ($fileExists) {
                git checkout "$originName/main" -- "$newClassName.java"
                git add "$newClassName.java"
                git commit -m "Sync $newClassName.java from $clientRepo"
            } else {
                Write-Host "  File $newClassName.java not found in $originName/main" -ForegroundColor Yellow
            }

        }
        
      #  git push
        
    }
    
}

function Get-UserClassName {
    param (
        [string]$className,
        [string]$user
    )
    
    # Convert user to UpperCamelCase
    $userCapitalized = $user.Substring(0,1).ToUpper() + $user.Substring(1)
    $userCapitalized = $userCapitalized -replace '-', ''
    
    # Replace 'User' in className with userCapitalized
    $newClassName = $className -replace 'User', $userCapitalized
    
    return $newClassName
}

function Set-ClassNameByUser {
    param (
        [string]$className,
        [string]$user
    )
    
    # Rename {className}.java to {className}-{user}.java and update class name
    if (Test-Path "$className.java") {

        # Get new class name
        $newClassName = Get-UserClassName -className $className -user $user
        
        $content = Get-Content "$className.java" -Raw
        $content = $content -replace "public class $className", "public class $newClassName"
        Set-Content "$newClassName.java" -Value $content
        Remove-Item "$className.java"
        git add "$newClassName.java"
        git add "$className.java"
        git commit -m "Rename $className to $newClassName"
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

    Set-Location $workingDir

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

function Create-LocalTestrs {
    param (
        [string]$unit,
        [array]$users
    )

    Set-Location "$workingDir\baraksu-class-2026-classroom-01-$unit"

    

    # Iterate over each repository
    foreach ($user in $users) {

    
        foreach ($className in @("HotelUserTester", "HotelRoomUserTester")) {

            $newClassName = Get-UserClassName -className $className  -user $user
            
            # Create file if it doesn't exist
            if (-not (Test-Path "$newClassName.java")) {
                
                # Check if template file exists
                if (Test-Path "$className.java") {
                    $content = Get-Content "$className.java" -Raw
                    $content = $content -replace "public class $className", "public class $newClassName"
                    Set-Content "$newClassName.java" -Value $content
                    Write-Host "Created $newClassName.java for $user" -ForegroundColor Green
                } else {
                    Write-Host "Template file $className.java not found" -ForegroundColor Yellow
                }
            }
        }
        
      #  git push
        
    }
    
}

function Get-LastWorkingUsers {
    param (
        [string]$unit,
        [array]$users,
        [int]$n = 1
    )
    
    Write-Host "Checking last commit activity for $unit repositories..." -ForegroundColor Cyan
    
    $userCommits = @()
    
    # Iterate over each repository
    foreach ($user in $users) {

        $clientRepo = $unit + '-' + $user
        
        # Check if repository exists on GitHub server
        $repoUrl = "https://baraksu-teacher@github.com/baraksu-class-2026/$clientRepo.git"
        $repoExists = git ls-remote $repoUrl 2>&1
        
        if ($LASTEXITCODE -eq 0) {
            # Repository exists, get last commit info
            $lastCommitDate = git ls-remote --heads $repoUrl main 2>&1 | Out-Null
            
            if ($LASTEXITCODE -eq 0) {
                # Use git log to get last commit date by this user
                try {
                    # Use the repository in working directory
                    $repoPath = Join-Path $workingDir $clientRepo
                    
                    if (-not (Test-Path $repoPath)) {
                        git clone "https://baraksu-teacher@github.com/baraksu-class-2026/$clientRepo.git" $repoPath 2>&1 | Out-Null
                    } else {
                        Push-Location $repoPath
                        git pull 2>&1 | Out-Null
                        Pop-Location
                    }
                    
                    if (Test-Path $repoPath) {
                        Push-Location $repoPath
                        
                        # Get last commit by this user using git log
                        $gitLog = git log --author="$user" --format="%H|%aI|%s" -n 1 2>&1
                        
                        if ($LASTEXITCODE -eq 0 -and $gitLog) {
                            $parts = $gitLog -split '\|'
                            if ($parts.Count -eq 3) {
                                $commitDate = [DateTime]::Parse($parts[1])
                                $commitMessage = $parts[2]
                                
                                $userCommits += [PSCustomObject]@{
                                    User = $user
                                    Date = $commitDate
                                    Message = $commitMessage
                                    Repo = $clientRepo
                                }
                            }
                        }
                        
                        Pop-Location
                    }
                } catch {
                    Write-Host "  Failed to get commit info for $user" -ForegroundColor Yellow
                    Write-Host "    Error: $($_.Exception.Message)" -ForegroundColor Red
                }
            }
        }
    }
    
    # Sort by date and display
    if ($userCommits.Count -gt 0) {
        Write-Host "`n Users sorted by last commit date:" -ForegroundColor Green
        $userCommits | Sort-Object -Property Date -Descending | ForEach-Object {
            $userName = $userDictionary."@$($_.User)"
            if ($userName) {
                $reversedName = -join $userName[-1..-$userName.Length]
                $displayName = $reversedName
            } else {
                $displayName = "@$($_.User)"
            }
            
            $timeAgo = (Get-Date) - $_.Date
            $timeString = if ($timeAgo.TotalDays -gt 1) {
                "$([int]$timeAgo.TotalDays) days ago"
            } elseif ($timeAgo.TotalHours -gt 1) {
                "$([int]$timeAgo.TotalHours) hours ago"
            } else {
                "$([int]$timeAgo.TotalMinutes) minutes ago"
            }
            
            Write-Host "  $displayName - $($_.Date.ToString('yyyy-MM-dd HH:mm')) ($timeString)" -ForegroundColor Cyan
            Write-Host "    $($_.Message)" -ForegroundColor Gray
        }
        
        $topN = if ($n -eq 1) { "Most recent commit by:" } else { "Top $n most recent commits by:" }
        Write-Host "`n$topN" -ForegroundColor Green
        $topUsers = $userCommits | Sort-Object -Property Date -Descending | Select-Object -First $n
        
        foreach ($latest in $topUsers) {
            $userName = $userDictionary."@$($latest.User)"
            if ($userName) {
                $reversedName = -join $userName[-1..-$userName.Length]
                Write-Host "  $reversedName (@$($latest.User))" -ForegroundColor Yellow
            } else {
                Write-Host "  @$($latest.User)" -ForegroundColor Yellow
            }
            Write-Host "    Date: $($latest.Date.ToString('yyyy-MM-dd HH:mm:ss'))" -ForegroundColor Yellow
            Write-Host "    Message: $($latest.Message)" -ForegroundColor Yellow
            write-Host "    Repository: $($latest.Repo)" -ForegroundColor Yellow
            Write-Host "" -ForegroundColor Yellow
        }
    } else {
        Write-Host "No commit information found for any user." -ForegroundColor Red
    }
}

# Call the function
 #Update-Repos -unit $unit -users $users
Update-MyRepos -unit $unit -users $users
Update-Secrets -unit $unit -users $users
#Check-ReposDoesExist -unit $unit -users $users
# Create-LocalTestrs -unit $unit -users $users
#Get-LastWorkingUsers -unit $unit -users $users -n 15