using UnityEngine;
using System.Collections;

public class mobSpawn : MonoBehaviour {
	public GameObject enemy1;
	public GameObject enemy2;
	public GameObject enemy3;
	public GameObject star;

	public int enemyLevel = 1;

	public int maxEnemy1 = 100;
	public int maxEnemy2 = 50;
	public int maxEnemy3 = 20;
	public int maxStar = 110;

	public static int enemyCount1 = 0;
	public static int enemyCount2 = 0;
	public static int enemyCount3 = 0;
	public static int starCount = 0;

	// Use this for initialization
	void Awake () {
		
	}
	
	// Update is called once per frame
	void Update () {
		if (GM.isOver)
			return;
		if (GM.stageLevel == 0) {
			maxEnemy1 = 80;
			maxEnemy2 = 0;
			maxEnemy3 = 0;
		}
		if (GM.stageLevel == 1) {
			maxEnemy1 = 100;
			maxEnemy2 = 30;
			maxEnemy3 = 0;
		}
		if (GM.stageLevel == 2) {
			maxEnemy1 = 100;
			maxEnemy2 = 50;
			maxEnemy3 = 10;
		}
		if (GM.stageLevel == 3) {
			maxEnemy1 = 100;
			maxEnemy2 = 50;
			maxEnemy3 = 15;
		}
		if (GM.stageLevel == 4) {
			maxEnemy1 = 100;
			maxEnemy2 = 50;
			maxEnemy3 = 20;
		}
		if (GM.stageLevel >= 5) {
			maxEnemy1 = 100;
			maxEnemy2 = 50;
			maxEnemy3 = 20;
		}
		for (int i = enemyCount1; i < maxEnemy1; i++) {
			GameObject e1 =  Instantiate(enemy1);
			e1.transform.position = new Vector2(Random.Range(-2048,2048),Random.Range(-2048,2048));
			enemyCount1++;
		}

		for (int i = enemyCount2; i < maxEnemy2; i++) {
			GameObject e2 =  Instantiate(enemy2);
			e2.transform.position = new Vector2(Random.Range(-2048,2048),Random.Range(-2048,2048));
			enemyCount2++;
		}

		for (int i = enemyCount3; i < maxEnemy3; i++) {
			GameObject e3 =  Instantiate(enemy3);
			e3.transform.position = new Vector2(Random.Range(-2048,2048),Random.Range(-2048,2048));
			enemyCount3++;
		}

		for (int i = starCount; i < maxStar; i++) {
			GameObject s =  Instantiate(star);
			s.transform.position = new Vector2(Random.Range(-2048,2048),Random.Range(-2048,2048));
			starCount++;
		}
	}
}
