Implemented classes DecryptlnputStream and EncryptOutputStream.
DecryptInputStream extends FilterInputStream and override
read functions. After reading symbol or array of symbols,
we decrypt(xor each symbol with specific key) them and then return.
EncryptOutputStream extends FilterOutputStream and override
write functions. Before writing we encrypt symbol or array of symbols
(xor each symbol with the same specific number).