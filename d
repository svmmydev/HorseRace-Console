[33mcommit 626f2d199caece0ea80d5f15c7a19a4f45b4e301[m[33m ([m[1;36mHEAD[m[33m -> [m[1;32mmain[m[33m, [m[1;31morigin/main[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Author: Roger <belegund@gmail.com>
Date:   Sun Oct 6 18:42:07 2024 +0200

    Resolve a bug where the game doesn't finish when all players lose because they have no bankroll

[33mcommit f76a671059c0a429b026cb22d823ec20a4fc35f1[m[33m ([m[1;31morigin/feature-game-manager[m[33m)[m
Author: Roger <belegund@gmail.com>
Date:   Fri Oct 4 10:38:23 2024 +0200

    Adds new methods to complete the game experience for better understanding of what is happening in the game. Also adds a loop on gameMenu() to allow multiple game and winning conditions based on players bankroll. For that it adds the bankroll field to player and methods to manage them

[33mcommit 178e1b63f37855922cc683973a304a5e340148de[m
Author: Roger <belegund@gmail.com>
Date:   Thu Oct 3 16:04:34 2024 +0200

    Adds Javadocs to most of the methods in model.PlayerManager

[33mcommit 9ee6ca98ea923621a7cfef93cb6d1f5160b199a4[m
Author: Roger <belegund@gmail.com>
Date:   Thu Oct 3 15:39:16 2024 +0200

    Adds model.PlayerManager class to manage the starting players/horses/bets and gets ready to start a horse race

[33mcommit 539a951197c06f5ec48982026f298df2d145bd17[m
Author: LeanEmanuel <leandrostruni@gmail.com>
Date:   Thu Oct 3 11:21:13 2024 +0200

    Class creation(Player,Human,Bot,Bet,GameHorseRace)versio1.3

[33mcommit 03796d13d330b6e1f545a51ace46cc87573051f4[m
Author: LeanEmanuel <leandrostruni@gmail.com>
Date:   Thu Oct 3 11:08:21 2024 +0200

    Class creation(Player,Human,Bot,Bet,GameHorseRace)2

[33mcommit 99d617c9710987ce85a2add6621603304bab56be[m
Author: LeanEmanuel <leandrostruni@gmail.com>
Date:   Thu Oct 3 11:02:12 2024 +0200

    Class creation(Player,Human,Bot,Bet,GameHorseRace)

[33mcommit ecc88f474e5c44d0b9a0bf4e8b76614a046db22f[m[33m ([m[1;31morigin/feature-pause[m[33m)[m
Author: Roger <belegund@gmail.com>
Date:   Thu Oct 3 10:49:17 2024 +0200

    Changes in comments

[33mcommit a60a630181f483eaffe515acd1ec929d497d659e[m
Author: Roger <belegund@gmail.com>
Date:   Thu Oct 3 10:47:35 2024 +0200

    Adds a new class Pause to make pauses betwen console outputs

[33mcommit 52db7667827c57ef26924c9663671af827153c02[m
Merge: 5857baa ad14bd2
Author: mariogn1987 <mariogn1987@gmail.com>
Date:   Thu Oct 3 09:42:27 2024 +0200

    translate to english

[33mcommit ad14bd2e53746e993413993f7009868804b69cf8[m[33m ([m[1;31morigin/feature-changelenguage[m[33m)[m
Author: mariogn1987 <mariogn1987@gmail.com>
Date:   Wed Oct 2 11:37:59 2024 +0200

    Transalate all comentaries to english

[33mcommit 5857baa29fd324779853ca3e1bd0d8f24b7b3e07[m[33m ([m[1;31morigin/feature-getdescription[m[33m)[m
Author: Roger <belegund@gmail.com>
Date:   Wed Oct 2 10:23:03 2024 +0200

    Adds new public abstract method getDescription() to the abstract class Card and implements it on the FacedCard and  NumberredCard subclasses. This method return a String with the card description.

[33mcommit ee8d054350b6505a50ddf82e74122a3ea39e2407[m
Author: Roger <belegund@gmail.com>
Date:   Sun Sep 29 18:00:35 2024 +0200

    Changes in comments

[33mcommit 1e80cd5524109f786903b4cc22512c8b1b85264b[m
Author: LeanEmanuel <leandrostruni@gmail.com>
Date:   Sun Sep 29 17:39:12 2024 +0200

    Correción errores paquetes

[33mcommit 674b7883f3fb8ecef1c6df8dd0cb37e7063f6a0d[m
Author: LeanEmanuel <leandrostruni@gmail.com>
Date:   Sun Sep 29 17:12:24 2024 +0200

    Reorganize files

[33mcommit 122a31ee2d2cb40f9ad1839cf053d7bf8767cdc9[m
Author: LeanEmanuel <leandrostruni@gmail.com>
Date:   Sun Sep 29 17:01:22 2024 +0200

    Initial commit
