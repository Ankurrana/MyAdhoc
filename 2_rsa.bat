@echo off
echo Input two random primes for RSA key generation process!
set /p Var1="Input p"
set /p Var2="Input q"
java RSA %var1% %var2%
pause