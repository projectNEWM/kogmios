package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockHeader(
    @SerialName("blockHeight")
    val blockHeight: Long,
    @SerialName("slot")
    val slot: Long,
    @SerialName("prevHash")
    val prevHash: String,
    @SerialName("issuerVk")
    val issuerVk: String,
    @SerialName("issuerVrf")
    val issuerVrf: String,
    @SerialName("blockSize")
    val blockSize: Long,
    @SerialName("blockHash")
    val blockHash: String,

    // optional for compact mode
    @SerialName("signature")
    val signature: String? = null,
    @SerialName("vrfInput")
    val vrfInput: CertifiedVrf? = null,
    @SerialName("nonce")
    val nonce: CertifiedVrf? = null,
    @SerialName("leaderValue")
    val leaderValue: CertifiedVrf? = null,
    @SerialName("opCert")
    val opCert: OpCert? = null,
    @SerialName("protocolVersion")
    val protocolVersion: ProtocolVersion? = null,
)

// {
//    "signature": "oKSvCDqlN/CblSrWZXmgB1nTNbrKxJHvK0tu7bH62NejTQwGRwseJLQA9B22JXO6PEkEiVAYFHsHgRWl0GWYAiJ7ScA74i+KuxfCtcCDWIImJWzOGx0VTUJNUhZWHXlMmEW5QtpQoeHBdSj3tqORcqhj1RCIAlFet/M///07o0j9Bq+tgSLNgRJi429Nb//HVIdyvlZh9TZArAD7h5y0eDkYHMvFza/1Shi4tR89sL1nrRHbkdRP93DVbWKLTlV4G616ivjDc00w5c0kcug09WygbbxNb2gg/Cfpd7mjj/Ihi+Gs6fshix2FP+tBCzv06uyWuKyyZrCqx2bWj0bnRteT473GJbT094+J+gbTxoRDNWTG7IzDXyJ6slLRR0xysqPsoxpmI8DLCff/caVpwvm0yW9xvi0/P3E+35wLONUARUlXUfAmS+WKF6miP46Ik5+tS0pgkk3owoL/NxnOCGI6S0MQfac1WC12iZMq5j7joVdJfqnIgCuLJ8rm4XvKS8HnPtbk6WI4uyt+l1jLCk/FFNKWKEVM4b1S/4NNc0swFwQ5nqOTdjjX7wyUEejXnipPRHCliYvsfeZ8ygFfEA==",
//    "nonce":
//    {
//        "output": "ZADB8x2EhA+9qd/Iz+pvnGjGrf39KtqbT5GTbG0HDyUPV/TkpV2V3tGYlOz0xDHPhIsWDKvlU+HkiRfeJgPB1g==",
//        "proof": "fQ9HHrc+BcW0wDhs3xMWOJ9njL6pYwsvPnikKG5C3iJxZSw5jvd6GI+WdUzUoFSIQ1RbysSWL67+1XDgg0c2RLsJCuhmcJQnBgxB+0CtqQ8="
//    },
//    "leaderValue":
//    {
//        "output": "ADe0OWX61qw83s9LdU/gzveaR65934sRN+XFPT7tC1dTkyA3ARQ7iZ84B8wQKUqljpoSSwX25kLYcRWX2kFUCg==",
//        "proof": "/nIT//pmUgGbq0q5BJOoE0J/sWuHaQEVoPQ5pyQMRpvFSVgdWbpSFPvWexLx71eG3lqdh5mS2iAvV+5+RQDs8jL7qHnal9U94gXTE48JcAc="
//    },
//    "opCert":
//    {
//        "hotVk": "YLnMl6j1k+x40JTqX/AB2iMQjiUoIfYmxAoKWhFDlIQ=",
//        "count": 3,
//        "kesPeriod": 96,
//        "sigma": "xm0xMyHD8szhod/fw2jpCUjnnPlnRzX3kPWadLrpeQNH7BcXkMwrMyFVufB5c6ykWsAE9y2Atbvtpl42cM37AQ=="
//    },
//    "protocolVersion":
//    {
//        "major": 3,
//        "minor": 0
//    },
//    "blockHeight": 2143410,
//    "slot": 13691505,
//    "prevHash": "d15be8fccd3dc354fc32b3ddb945471cdd7331717f711bea1bb41ecc157d5d24",
//    "issuerVk": "a5beb9c87c05c1921dba64c64f10c7b8d32a4c81344b359aece05317059cadbe",
//    "issuerVrf": "43qWWJS6oveKahD45nSQsuIiN3wTVa19jcEknbHAg3o=",
//    "blockSize": 373,
//    "blockHash": "caaf3559e4d3b406fc880ab3496ef21c152154dfe47712c738cb5bbc53d3651e"
// }
