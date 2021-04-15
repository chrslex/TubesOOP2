package OOPokemon.Skill;

public class SkillHashFunction
{
	public int hash(final Skill s)
	{
		return s.skillName.length() + s.skillType.length();
	}
}