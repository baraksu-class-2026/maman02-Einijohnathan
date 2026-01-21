# Change to GitHub directory

$script:workingDir = "C:\Users\bsuberri\OneDrive - NI\Documents\GitHub"
$script:userDictPath = Join-Path $PSScriptRoot 'users_dictionary.json'
$script:userDictionary = Get-Content -Path $script:userDictPath -Raw | ConvertFrom-Json
$script:classroomUrl = "https://baraksu-teacher@github.com/baraksu-class-2026"

# List of client repositories
$script:unit = 'maman02'
$script:clientRepo = ''

# $script:users = @(
#             'dvirshg'

#         )

$script:users = @(
    'baraksu-teacher',
    'ArielMeyer1',
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
            
            git clone  $classroomUrl/$clientRepo.git
            
            if (-not (Test-Path $clientRepo)) {
                continue
            }

        }

        Set-Location $clientRepo

        git pull

        $remotes = git remote
        
        if ($remotes -notcontains 'teacher') {
            git remote add teacher $classroomUrl/baraksu-class-2026-classroom-01-$unit
        }

        git fetch teacher main

        git merge teacher/main

        if ($LASTEXITCODE -ne 0) {
            Write-Host "  Failed to merge teacher/main into $clientRepo" -ForegroundColor Red
            Write-Host "  There may be merge conflicts that need to be resolved manually" -ForegroundColor Yellow
            Set-Location ".."
            continue
        }

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
        
        $repoUrl = "$classroomUrl/$clientRepo.git"
        $repoExists = git ls-remote $repoUrl 2>&1
        
        if ($LASTEXITCODE -ne 0) {
            continue
        }
        
        if ($remotes -notcontains $originName) {
            git remote add $originName $classroomUrl/$clientRepo.git
        }

        git fetch $originName main

        if ($LASTEXITCODE -ne 0) {
            Write-Host "  Failed to fetch from $originName" -ForegroundColor Red
            continue
        }

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
        $repoUrl = "$classroomUrl/$clientRepo.git"
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
            
            git clone  $classroomUrl/$clientRepo.git
            
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
        [int]$n = 1,
        [switch]$Descending
    )
    
    Write-Host "Checking last commit activity for $unit repositories..." -ForegroundColor Cyan
    
    # Use parallel processing for better performance
    $userCommits = $users | ForEach-Object -ThrottleLimit 5 -Parallel {
        $user = $_
        $clientRepo = $using:unit + '-' + $user
        $workingDir = $using:workingDir
        $classroomUrl = $using:classroomUrl
        
        # Check if repository exists on GitHub server
        $repoUrl = "$classroomUrl/$clientRepo.git"
        $repoExists = git ls-remote $repoUrl 2>&1
        
        if ($LASTEXITCODE -eq 0) {
            try {
                # Use the repository in working directory
                $repoPath = Join-Path $workingDir $clientRepo
                
                if (-not (Test-Path $repoPath)) {
                    git clone --quiet "$classroomUrl/$clientRepo.git" $repoPath 2>&1 | Out-Null
                } else {
                    Push-Location $repoPath
                    git pull --quiet 2>&1 | Out-Null
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
                            
                            Pop-Location
                            
                            return [PSCustomObject]@{
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
                Write-Error "Failed to get commit info for $user : $($_.Exception.Message)"
            }
        }
    }
    
    # Sort by date and display
    if ($userCommits.Count -gt 0) {
        $topN = if ($n -eq 1) { "Most recent commit by:" } else { "Top $n most recent commits by:" }
        Write-Host "`n$topN" -ForegroundColor Green
        
        $topCommits = if ($Descending) {
            $userCommits | Sort-Object -Property Date -Descending | Select-Object -First $n
        } else {
            $userCommits | Sort-Object -Property Date | Select-Object -First $n
        }
        
        foreach ($commit in $topCommits) {
            $userName = $userDictionary."@$($commit.User)"
            if ($userName) {
                $reversedName = -join $userName[-1..-$userName.Length]
                $displayName = $reversedName
            } else {
                $displayName = "@$($commit.User)"
            }
            
            $timeAgo = (Get-Date) - $commit.Date
            $timeString = if ($timeAgo.TotalDays -gt 1) {
                "$([int]$timeAgo.TotalDays) days ago"
            } elseif ($timeAgo.TotalHours -gt 1) {
                "$([int]$timeAgo.TotalHours) hours ago"
            } else {
                "$([int]$timeAgo.TotalMinutes) minutes ago"
            }
            
            Write-Host "  $displayName - $($commit.Date.ToString('yyyy-MM-dd HH:mm')) ($timeString)" -ForegroundColor Cyan
            Write-Host "    $($commit.Message)" -ForegroundColor Gray
            Write-Host "  $displayName (@$($commit.User))" -ForegroundColor Yellow
            Write-Host "    Date: $($commit.Date.ToString('yyyy-MM-dd HH:mm:ss'))" -ForegroundColor Yellow
            Write-Host "    Message: $($commit.Message)" -ForegroundColor Yellow
            Write-Host "    Repository: https://github.com/baraksu-class-2026/$($commit.Repo)" -ForegroundColor Yellow
            Write-Host "" -ForegroundColor Yellow
        }
    } else {
        Write-Host "No commit information found for any user." -ForegroundColor Red
    }
}

function Print-LastGrade {
    param (
        [string]$unit,
        [array]$users
    )

    Set-Location $workingDir
    # Iterate over each repository
    foreach ($user in $users) {

        Write-Host "Processing repository: $clientRepo" -ForegroundColor Cyan

        $clientRepo = $unit + '-' + $user
        
        if (-not (Test-Path $clientRepo)) {
            
            git clone --quiet $classroomUrl/$clientRepo.git 2>&1 | Out-Null
            
            if (-not (Test-Path $clientRepo)) {
                continue
            }

        }

        Set-Location $clientRepo

        git pull --quiet 2>&1 | Out-Null

        # Print grade badge content if exists
        $gradeBadgePath = ".github\badges\grade.md"
        if (Test-Path $gradeBadgePath) {
            $userName = $userDictionary."@$($user)"
            if ($userName) {
                $reversedName = -join $userName[-1..-$userName.Length]
                $displayName = $reversedName
            } else {
                $displayName = "@$user"
            }
            Write-Host "`n$displayName (@$user)" -ForegroundColor Cyan
            $gradeContent = Get-Content $gradeBadgePath -Raw
            
            # Extract and parse the badge image URL
            if ($gradeContent -match '!\[.*?\]\((https://img\.shields\.io/badge/([^-]+)-([^-]+)-([^\)]+))\)') {
                $badgeImageUrl = $matches[1]
                $label = $matches[2]
                $value = $matches[3] -replace '%25', '%'
                $color = $matches[4]
                
                # Map shield.io colors to PowerShell colors
                $psColor = switch -Regex ($color) {
                    'brightgreen|success|green' { 'Green' }
                    'yellow|warning' { 'Yellow' }
                    'red|critical|important' { 'Red' }
                    'blue|informational' { 'Cyan' }
                    'orange' { 'DarkYellow' }
                    default { 'White' }
                }
                
                Write-Host "  ╔════════════════════════════════╗" -ForegroundColor DarkGray
                Write-Host "  ║ " -NoNewline -ForegroundColor DarkGray
                Write-Host "$label" -NoNewline -ForegroundColor White
                Write-Host ": " -NoNewline -ForegroundColor DarkGray
                Write-Host "$value" -NoNewline -ForegroundColor $psColor
                Write-Host (" " * (27 - $label.Length - $value.Length)) -NoNewline
                Write-Host "║" -ForegroundColor DarkGray
                Write-Host "  ╚════════════════════════════════╝" -ForegroundColor DarkGray
                Write-Host "  Repository: $classroomUrl/$clientRepo " -ForegroundColor DarkGray
            } else {
                Write-Host $gradeContent -ForegroundColor Green
            }
        }
        
        Set-Location ".."


    }
}

# When imported as a module, all functions are automatically exported
# Call the function when script is run directly (not imported as module)
# Uncomment the function you want to run:
# Update-Repos -unit $script:unit -users $script:users
# Update-MyRepos -unit $script:unit -users $script:users
# Update-Secrets -unit $script:unit -users $script:users
# Check-ReposDoesExist -unit $script:unit -users $script:users
# Create-LocalTestrs -unit $script:unit -users $script:users
# Get-LastWorkingUsers -unit $script:unit -users $script:users -n 10 -Descending
# Print-LastGrade -unit $script:unit -users $script:users