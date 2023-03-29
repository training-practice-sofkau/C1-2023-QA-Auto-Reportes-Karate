Feature: Obtener la lista de NFTs de CoinGecko usando Karate

  Como usuario interesado en los NFTs,
  Quiero obtener una lista de los NFTs disponibles en CoinGecko
  Para poder conocer los NFTs populares y estar al día con las últimas tendencias


  Scenario: Obtener la lista de NFTs disponibles
    Given url 'https://api.coingecko.com/api/v3/nfts/list'
    When method get
    Then status 200
    And match response contains {id: '#string', contract_address: '#string', name: '#string', asset_platform_id: '#string', symbol: '#string'}

  Scenario Outline: Obtener la lista de NFTs filtrado por el id de la blockchain
    Given url 'https://api.coingecko.com/api/v3'
    And path 'nfts/'+<id>
    When method get
    Then status 200
    And match response contains {id: <id>, contract_address: <contract_address>, name: '#string', asset_platform_id: '#string'}
    And match $.id == <id>
    Examples:
      | id               | contract_address                           |
      | "squiggly"       | 0x36F379400DE6c6BCDF4408B282F8b685c56adc60 |
      | "angry-ape-army" | 0x77640cf3F89A4F1B5CA3A1e5c87f3F5B12ebf87e |
      | "meebits"        | 0x7Bd29408f11D2bFC23c34f18275bBf23bB716Bc7 |

